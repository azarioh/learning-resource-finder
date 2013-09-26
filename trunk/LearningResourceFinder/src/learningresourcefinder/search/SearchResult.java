package learningresourcefinder.search;

import javax.persistence.EntityManager;

import learningresourcefinder.model.User;
import learningresourcefinder.model.User.AccountStatus;
import learningresourcefinder.security.SecurityContext;
import learningresourcefinder.service.IndexManagerService;

import org.apache.lucene.document.Document;


public  class SearchResult {
    
    private long id;
    private String title;  // short text displayed as title in the search list.
    private String text = "";  // Highlighted content if any (else, a substitution text such as Article.description)
    private String url;  // for example: "/article/my_Article" or "user/toto".
    private String entityClassName;
    private boolean visible = false;
    
    public SearchResult(String keyWord, Document doc, 
            IndexManagerService indexManagerService,
            EntityManager em){
        // - get the entity (get the class name, get the id, perform the find) 
         entityClassName = doc.get("className");
        
        id = Long.valueOf(doc.get("id"));
        
        User current = null;
        
        if(SecurityContext.isUserLoggedIn()){
            
            current = SecurityContext.getUser();
        }
        // - this.title gets the highlighted Article.title or User.getFullName() or Action.title or GoodExample.title
        if ( entityClassName.equals(User.class.getSimpleName()) ){
            
            User user = em.find(User.class, id);
            
            if((!user.getAccountStatus().equals(AccountStatus.ACTIVE) && current != null && current.hasAdminPrivileges()) || user.getAccountStatus().equals(AccountStatus.ACTIVE)){
                visible = true;
            }
            
            title =  user.getFullName();
            url = "user/"+user.getUserName();
        }
        
        /*else { 
        	
        	// Ahmed-flag
        	
        	// Article, Action, GoodExample
            //TODO setup condition for visible ????
            visible = true;
            
            title = indexManagerService.getHighlight(keyWord, doc.get("title"));
            if (title==null || title.isEmpty()){
                title=doc.get("title");
            }
            if(entityClassName.equals(Article.class.getSimpleName())){
                Article article = em.find(Article.class, id);
              url = "/article/"+article.getUrl();
            }else if (entityClassName.equals(Action.class.getSimpleName())){
                Action action = em.find(Action.class,id);
                url = "/action/"+action.getUrl();
                
            } else if(entityClassName.equals(GoodExample.class.getSimpleName())){
                GoodExample goodExample = em.find(GoodExample.class,id);*/
                
                //TODO implement getUrl() in goodexample and set pathVariable in controller , uncomment this :
              //    url ="/goodexample/"+goodExample.getUrl();
           //    url = "/home"; 
          //  }
      // }

        // idem for text. if highligt
      /*  for (IndexableField field : doc.getFields()) {
            
            if(!"id".equals(field.name()) && !"className".equals(field.name()) && (("mail".equals(field.name())  && SecurityContext.isUserHasPrivilege(Privilege.MANAGE_USERS)) ||
               ("toClassify".equals(field.name())  && SecurityContext.isUserHasPrivilege(Privilege.VIEW_UNPUBLISHED_ARTICLE)))){
                
                @SuppressWarnings("unused")
                
                
                String fielddebug = field.stringValue();
                String hightlight = indexManagerService.getHighlight(keyWord,field.stringValue());
                
                text += (hightlight != null) ? (hightlight + "<br/>") : "";
            }
        }
        if (text.isEmpty()) { // If after all that text is still empty, we'll try to get non highlighted text.
            text = doc.get("description") != null ? doc.get("description") : "no description"; // If no description field (i.e. user, the text will be empty).
            
        }*/

    }
        

    public boolean isForClass(Class clazz) {
    	return this.getEntityClassName().equals(clazz.getSimpleName());
    }
    
    public String getText() {
       
        return text;
    }

    public String getTitle() {
      
        return title;
    }

   public long getId(){
       
       
       return id;
   }

   
   public String getUrl(){
       
       return url;
   }
  
    public String getEntityClassName(){
        
        return entityClassName;
    }
    
    public boolean isVisible(){
        
        return visible;
    }


	@Override
	public String toString() {
		return "SearchResult [id=" + id + ", title=" + title + ", text=" + text
				+ ", url=" + url + ", entityClassName=" + entityClassName
				+ ", visible=" + visible + "]";
	}

    
    
}
