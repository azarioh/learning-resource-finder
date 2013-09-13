package learningresourcefinder.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import learningresourcefinder.model.Competence;
import learningresourcefinder.model.PlayList;
import learningresourcefinder.model.Resource;
import learningresourcefinder.repository.UserRepository;
import learningresourcefinder.search.Searchable;
import learningresourcefinder.security.Privilege;
import learningresourcefinder.security.SecurityContext;
import learningresourcefinder.util.CurrentEnvironment;
import learningresourcefinder.util.FileUtil;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component 
@Transactional
/**
 * Manage Lucene library (create, search, update the index).
 * 
 * Don't use it directly to run a search--> use ArticleSearchResultsManager
 */
public class IndexManagerService {
	
	public static final int MAX_HITS = 1000;

	@Autowired UserRepository userRepository;
	@Autowired CurrentEnvironment currentEnvironment;

	
    @PersistenceContext EntityManager em;

    @SuppressWarnings("unchecked")   
    Class<Searchable>[] searchables = new Class[]{Resource.class, PlayList.class, Competence.class};  // Entities that are included in the index.
   
    String[] searchableCriterias = new String[]{    // Addition of all the fields that should be searchable in the searchable entities.
            "name", "description"};  

    
	@SuppressWarnings("unchecked")
    public void createIndexes() {
		try(SimpleFSDirectory sfsd = new SimpleFSDirectory(new File(FileUtil.getLuceneIndexDirectory(currentEnvironment)))){
			
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);

			try(IndexWriter writer = new IndexWriter(sfsd, iwc)){// Make an writer to create the index (with try-with-resources block)
				// Index all Accommodation entries
			    
		
			    for (Class<Searchable> searchableClass : searchables) {
			        List<Searchable> searchableList =  em.createQuery("select e from "+searchableClass.getName()+" e").getResultList();
	                for (Searchable searchable : searchableList) {
	                    writer.addDocument(createDocument(searchable));
	                }
			    }
			
				writer.commit();
			}               
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}	



	private Document createDocument(Searchable searchable) {
		// Add a new Document to the index
        Document doc = new Document();
        
        doc.add(new TextField("id",String.valueOf(searchable.getId()), Store.YES));   // TODO Test with Store.NO, I don't see any reason to make id searchable.
        doc.add(new TextField("className",searchable.getClass().getSimpleName(),Store.YES));
        
        Map<String, String> criteriaMap = searchable.getCriterias();
        
        // This give more importance to title during the search
        // The user see the result from the title before the result from the text 
        String boostedName = searchable.getBoostedCriteriaName();
        Field boostedField = new TextField(boostedName,criteriaMap.get(boostedName),Store.YES);
        boostedField.setBoost(1.5f);
        doc.add(boostedField);
        
        for(String fieldname : criteriaMap.keySet()){
            if(!fieldname.equals(boostedName)){
              doc.add(new TextField(fieldname, criteriaMap.get(fieldname), Store.YES));
            }
        }
        
        return doc;
	}
	

	
	public void update(Searchable searchable) {
        try(IndexWriter writer = getIndexWriter()){
        	
            writer.updateDocument(new Term("id", String.valueOf(searchable.getId())), createDocument(searchable));
            writer.commit();
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void delete(Searchable searchable) {
		 try(IndexWriter writer = getIndexWriter()){
            writer.deleteDocuments(new Term("id", String.valueOf(searchable.getId())));
            writer.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void add(Searchable searchable) {
		 try(IndexWriter writer = getIndexWriter()){
            writer.addDocument(createDocument(searchable));
            writer.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * Used to search the index, using one or more keywords
	 * @param privilegeNeededToSeachAllCriteria privilege needed to search in all fields. For example Privilege.VIEW_UNPUBLISHED_ARTICLE to see Article.toClassify.
	 * @return
	 */
	public ScoreDoc[] search(String keyWords) {

        try {
        	// We  build a query using the parameters;
            String queryString="(" + keyWords + "~)"; //the "~" enable fuzzy search

            SimpleFSDirectory sfsd = new SimpleFSDirectory(new File(FileUtil.getLuceneIndexDirectory(currentEnvironment)));
            IndexReader reader = DirectoryReader.open(sfsd);
            IndexSearcher searcher = new IndexSearcher(reader);
            
            // Create the search criteria as an array with article fields we search in.
            // We need all the fields for 2 reasons:
            //   1. It seems (to verify) that lucene does not work that well if we just tell "all the fields" instead of listing all the fields.
            //   2. It seems that Lucene has no way to say "search all the fields except the field 'toClassify'".
            
            List<String> fieldList = new ArrayList<String>(Arrays.asList(searchableCriterias));

            if (!SecurityContext.isUserHasPrivilege(Privilege.MANAGE_USERS)) {
                fieldList.remove("mail");
            }
			//.....
			
			String[] fieldArray = new String[fieldList.size()];
			fieldList.toArray(fieldArray);
			
            // Build a Query object
            MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_40, fieldArray, new StandardAnalyzer(Version.LUCENE_40));
            Query query = parser.parse(queryString);
            
            query.rewrite(reader);

            TopDocs hits=searcher.search(query, MAX_HITS);
            sfsd.close();
            return hits.scoreDocs;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	  /**
     * We use the lucene highlight library to highlight text. For example if we search for "banana", and it apprears once in a longtext
     * we need a few words before and after to display the search results, like: "... sweet yellow <b>banana</b>s are ..."
     * @return the highlighted fragment, if there is one, null otherwise. 
     */
    public String getHighlight(String keyword, String textToHighlight){
        try (SimpleFSDirectory sfsd = new SimpleFSDirectory(new File(FileUtil.getLuceneIndexDirectory(currentEnvironment)))){
        	
        	
            try (IndexReader reader = DirectoryReader.open(sfsd)){
	        	String queryString="(" + keyword + "~)";
	            QueryParser queryParser = new QueryParser(Version.LUCENE_40, "field", new StandardAnalyzer(Version.LUCENE_40));//(new Term("field", keyword));
	            Query query = queryParser.parse(queryString);
	            query.rewrite(reader);
	            
	            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<b>","</b>");
	            QueryScorer scorer = new QueryScorer(query,"field");
	            Highlighter highlighter = new Highlighter(formatter,scorer);
	            highlighter.setTextFragmenter(new SimpleSpanFragmenter(scorer,45));
	            try (Analyzer analyzer =new StandardAnalyzer(Version.LUCENE_40)){
		            try(TokenStream tokens = analyzer.tokenStream("field",new StringReader(textToHighlight))){
		            	String highlightedFragment = highlighter.getBestFragments(tokens, textToHighlight,4 ,"<BR/>...");
		            	if(highlightedFragment.equals("")){
		                	return null;
		                }else{
		                	return highlightedFragment;
		                }
		            }
	            }
            }
        } catch (IOException | InvalidTokenOffsetsException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
	public Document findDocument(ScoreDoc scoreDoc){
		File file = new File(FileUtil.getLuceneIndexDirectory(currentEnvironment));
        try (SimpleFSDirectory sfsd = new SimpleFSDirectory(file)){
            try (IndexReader reader = DirectoryReader.open(sfsd)){
	            IndexSearcher searcher = new IndexSearcher(reader);        
	            Document doc = searcher.doc(scoreDoc.doc);
	            return doc;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void removeIndexes(){
		File file = new File(FileUtil.getLuceneIndexDirectory(currentEnvironment));
        try(SimpleFSDirectory sfsd = new SimpleFSDirectory(file)) {
            String[] listFile=sfsd.listAll();
            for(String fileStr : listFile){
                sfsd.deleteFile(fileStr);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	 /**
     * Return an IndexWriter allows to read and write in the index of lucene 
     */
    private IndexWriter getIndexWriter() throws IOException, CorruptIndexException {
    	SimpleFSDirectory sfsd = new SimpleFSDirectory(new File(FileUtil.getLuceneIndexDirectory(currentEnvironment)));
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, new StandardAnalyzer(Version.LUCENE_40));
        return new IndexWriter(sfsd, iwc);
    }

    /*public void deleteAction(Action action) {
        // TODO implement me
        
    }*/ // Ahmed-Flag
    
}
