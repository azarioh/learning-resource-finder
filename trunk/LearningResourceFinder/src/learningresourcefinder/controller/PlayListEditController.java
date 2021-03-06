package learningresourcefinder.controller;

import javax.validation.Valid;

import learningresourcefinder.model.PlayList;
import learningresourcefinder.model.Resource;
import learningresourcefinder.repository.PlayListRepository;
import learningresourcefinder.security.SecurityContext;
import learningresourcefinder.service.IndexManagerService;
import learningresourcefinder.service.LevelService;
import learningresourcefinder.util.Action;
import learningresourcefinder.util.NotificationUtil;
import learningresourcefinder.util.NotificationUtil.Status;
import learningresourcefinder.web.Slugify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/playlist")
public class PlayListEditController extends BaseController<PlayList>{
	@Autowired PlayListRepository playlistRepository;
	@Autowired IndexManagerService indexManager;
	@Autowired LevelService levelService;
	
	 private ModelAndView prepareModelAndView(PlayList playlist ) {
	        ModelAndView mv = new ModelAndView("playlistedit");
	        mv.addObject("id", playlist.getId());
	        mv.addObject("playlist", playlist); 
	        return mv; 
	    }
	

	@RequestMapping("/create")
	public ModelAndView playListCreate() {
	    SecurityContext.assertUserIsLoggedIn();
	    return prepareModelAndView(new PlayList());
	}
	
	@RequestMapping("/edit")
	public ModelAndView playListEdit (@RequestParam("id")long id) {
		PlayList playlist =(PlayList) getRequiredEntity(id,PlayList.class);
		
		SecurityContext.assertCurrentUserMayEditThisPlaylist(playlist);
		return prepareModelAndView(playlist);
		
	}
	@RequestMapping("/editsubmit")
	public ModelAndView playListEditSubmit(@Valid @ModelAttribute PlayList playList, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return new ModelAndView("playlistedit","playList",playList);
		}
		
		if (playList.getId() != null) { // Existing playlist (not creating)
		    SecurityContext.assertCurrentUserMayEditThisPlaylist(playList);
		}
		
		PlayList playListHavingTheSameName = playlistRepository.findByNameAndAuthor(playList.getName(), SecurityContext.getUser());

		// set the slug based on the (maybe changed) title
		//playlist.setSlug( tu vas appeler une fonction qui prend playList.getTitle() en paramètre );
		String slug = Slugify.slugify(playList.getName());
		playList.setSlug(slug);
		if(playList.getId()==null) {  // Create
			if(playListHavingTheSameName != null ) {
				NotificationUtil.addNotificationMessage("Cet intitulé existe déjà pour une de vos séquences, veuillez en choisir un autre", Status.WARNING);
				return otherPlayListError(playList, playListHavingTheSameName, bindingResult);
			} 
			playlistRepository.persist(playList);
			indexManager.add(playList);
            levelService.addActionPoints(SecurityContext.getUser(), Action.ADD_PLAYLIST); 
			
		} else {  // Edit existing playlist
			if (playListHavingTheSameName != null && !playList.equals(playListHavingTheSameName)) {
				return otherPlayListError(playList, playListHavingTheSameName, bindingResult);
			}
			playlistRepository.merge(playList);
			indexManager.update(playList);
		}
		
		return new ModelAndView("redirect:/playlist/"+playList.getShortId()+"/"+playList.getSlug());
	}
	

	@RequestMapping("/delete")
	public ModelAndView playListDelete (@RequestParam("id")long id) {
	    PlayList playlist =(PlayList) getRequiredEntity(id, PlayList.class);

	    SecurityContext.assertCurrentUserMayEditThisPlaylist(playlist);

	    if (playlist!= null) {
	        NotificationUtil.addNotificationMessage("Séquence "+ playlist.getName() + " supprimée", Status.SUCCESS);
	        playlistRepository.remove(playlist);
	    }

	    return new ModelAndView("redirect:/playlist/user/"+ SecurityContext.getUser().getUserName());

	}
	
	private ModelAndView otherPlayListError(PlayList playList, PlayList otherPlayList, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView ("playlistedit", "playlist", playList);
		bindingResult.rejectValue("name", "sss", "Une de vos séquences a déjà ce titre. Merci d'en choisir un différent.");
		return mv;
	}

	
	@RequestMapping("/remove")
    public ModelAndView playListRemoveResource(@RequestParam("idplaylist")long idPlayList,@RequestParam("idresource")long idResource) {
        PlayList playlist = (PlayList) getRequiredEntity(idPlayList,PlayList.class);
        Resource resource = (Resource) getRequiredEntity(idResource,Resource.class);
        
        SecurityContext.assertCurrentUserMayEditThisPlaylist(playlist);
        
        if (playlist!= null && resource!=null){
            playlist.getResources().remove(resource);
            playlistRepository.merge(playlist);
            indexManager.update(playlist);
        }
        return new ModelAndView("redirect:/playlist/"+playlist.getShortId()+"/"+playlist.getSlug());
    }
	
	@RequestMapping("/add")
    public ModelAndView playListAddResource(@RequestParam("idplaylist")long idPlayList,@RequestParam("idresource")long idResource) {
        PlayList playlist = (PlayList) getRequiredEntity(idPlayList,PlayList.class);
        Resource resource = (Resource) getRequiredEntity(idResource,Resource.class);
        
        SecurityContext.assertCurrentUserMayEditThisPlaylist(playlist);
        
        if (playlist!= null && resource!=null){
            playlist.getResources().add(resource);
            playlistRepository.merge(playlist);
            indexManager.update(playlist);
        }
        return new ModelAndView("redirect:/playlist/"+playlist.getShortId()+"/"+playlist.getSlug());
    }
	
    @ModelAttribute
    public PlayList findPlayList(@RequestParam(value="id",required=false)Long id){
        if(id==null){
            //create
            return new PlayList(); 
        }else {

            return (PlayList)getRequiredDetachedEntity(id);

        }
    }

}
