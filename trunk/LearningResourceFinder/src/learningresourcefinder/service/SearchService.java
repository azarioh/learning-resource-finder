package learningresourcefinder.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.ScoreDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reformyourcountry.search.SearchResult;

@Component
public class SearchService {

	@Autowired
	private IndexManagerService indexManagerService;

	@PersistenceContext
	EntityManager em;

	private List<SearchResult> searchResultList;

	public List<SearchResult> search(String keyWord) {
		searchResultList = new ArrayList<SearchResult>();
		ScoreDoc[] scoreDocs = indexManagerService.search(keyWord);
		for (ScoreDoc sd : scoreDocs) {
			searchResultList.add(new SearchResult(keyWord, indexManagerService
					.findDocument(sd), indexManagerService, em));
		}
		return searchResultList;
	}

}
