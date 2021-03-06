package learningresourcefinder.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import learningresourcefinder.model.Competence;
import learningresourcefinder.model.PlayList;
import learningresourcefinder.model.Resource;
import learningresourcefinder.repository.ResourceRepository;
import learningresourcefinder.search.SearchResult;
import learningresourcefinder.service.IndexManagerService;
import learningresourcefinder.service.SearchService;
import learningresourcefinder.web.Cache;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchSummaryController extends BaseController<Resource> {
	
	
	@Autowired private SearchService searchService;
	@Autowired private IndexManagerService indexManagerService;
	@Autowired private ResourceRepository resourceRepository;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam("searchphrase") String searchPhrase) throws UnsupportedEncodingException {
	    
	    if (searchPhrase != null)
	        searchPhrase = new String(searchPhrase.getBytes("ISO-8859-1"), "UTF-8"); // Replacement of wrong characters in the word.

		List<SearchResult> listOfResult = searchService.search(searchPhrase);
		List<Competence> listOfCompetence = (List)searchService.getFirstEntities(listOfResult, 50, Competence.class);
		int numberResource = listOfResult.size();
		ModelAndView mv = new ModelAndView("searchsummary");
		mv.addObject("resourceList", searchService.getFirstEntities(listOfResult, 5, Resource.class));
		mv.addObject("playlistList", searchService.getFirstEntities(listOfResult, 5, PlayList.class));
		mv.addObject("competenceList", listOfCompetence);
		mv.addObject("searchPhrase", searchPhrase);
		mv.addObject("numberResource", numberResource);
		return mv;
	}
	
	  @RequestMapping(value="/ajax/autocomplete")
	    public @ResponseBody JSONObject firstmatch ( @RequestParam("prefix") String prefix){
	        JSONObject jSonResponse = new JSONObject();

	        int i = 0;
	        List<String> autocompleteList = Cache.getInstance().getAutocompleteSuggestions(prefix);
	        for (String autocompleteString : autocompleteList) {
	            jSonResponse.put(i++, autocompleteString);      
	        }     
	        return jSonResponse;
	        
	    }
	
}
