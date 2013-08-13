package learningresourcefinder.controller;


import javax.servlet.http.HttpServletRequest;

import learningresourcefinder.exception.InvalidPasswordException;
import learningresourcefinder.exception.UserLockedException;
import learningresourcefinder.exception.UserNotFoundException;
import learningresourcefinder.exception.UserNotValidatedException;
import learningresourcefinder.model.User;
import learningresourcefinder.model.User.AccountConnectedType;
import learningresourcefinder.repository.UserRepository;
import learningresourcefinder.service.LoginService;
import learningresourcefinder.service.LoginService.WaitDelayNotReachedException;
import learningresourcefinder.util.DateUtil;
import learningresourcefinder.util.NotificationUtil;
import learningresourcefinder.web.Cookies;
import learningresourcefinder.web.UrlUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController<User> {

    public static final String AUTOLOGIN_KEY = "autologin";
    public static final String PROVIDERSIGNEDIN_KEY = "providersignedin";
    
    @Autowired LoginService loginService;
    @Autowired UserDisplayController userDisplayController;
    @Autowired ProviderSignInController providerSignIncontroller;
    @Autowired UsersConnectionRepository usersConnectionRepository;
    @Autowired UserRepository userRepository;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView signin(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("login");

        // Should we pre-check the "autologin" checkbox ?
        Boolean autologin  = (Boolean) (request).getSession().getAttribute(AUTOLOGIN_KEY);
        autologin = autologin == null ? false : autologin;
        autologin = autologin || Cookies.findCookie(Cookies.LOGINCOOKIE_KEY) != null;
        mv.addObject(AUTOLOGIN_KEY, autologin);
        
        return mv;
    }

    
    /**
     * 
     * @param password required=false because we don't use pswd in DEV
     * @param keepLoggedIn required=false because when user don't check the checkbox we get a 400 error
     * @return
     */
    @RequestMapping("/loginsubmit")
    public ModelAndView loginSubmit(@RequestParam("identifier") String userNameOrMail,
            @RequestParam(value="password",required=false) String password,
            WebRequest request) {

        
        String errorMsg = null;
        User user = null;
        try {
            Boolean autologin = loginService.readAutoLogin(request);          
            user = loginService.login(userNameOrMail, password, autologin, null, AccountConnectedType.LOCAL);
            NotificationUtil.addNotificationMessage("Vous êtes à present connecté sur "+UrlUtil.getWebSiteName());

        } catch (UserNotFoundException e) {
            errorMsg="L'utilisateur '"+userNameOrMail+"' n'existe pas";

        } catch (InvalidPasswordException e) {
            

            try {
                User retrieveuser = loginService.identifyUserByEMailOrName(userNameOrMail) ;
                
                errorMsg = loginService.getRemainderLoginMessage(retrieveuser);
               
            } catch (UserNotFoundException e1) {
                errorMsg="L'utilisateur '"+userNameOrMail+"' n'existe pas";
            }
           
           if (StringUtils.isBlank(errorMsg)){
               errorMsg="Ce mot de passe n'est pas valide pour l'utilisateur '"+userNameOrMail+"'";
           }

        } catch (UserNotValidatedException e) {
            errorMsg="L'utilisateur '"+userNameOrMail+"' n'a pas été valide. Vérifiez vos mails reçus et cliquez sur le lien du mail qui vous a été envoyé à l'enregistrement.";

        } catch (UserLockedException e) {
            errorMsg="L'utilisateur  '"+userNameOrMail+"' est verrouillé. Contacter un administrateur pour le déverrouiller.";

        } catch (WaitDelayNotReachedException e) {
            errorMsg="Suite à de multiples tentatives de login échouées, votre utilisateur s'est vu imposé un délai d'attente avant de pouvoir se relogguer, ceci pour des raisons de sécurité." +
                    " Actuellement, il reste "+ DateUtil.formatIntervalFromToNow(e.getNextPossibleTry()) +" à attendre.";
        }

     
        
        
        if (errorMsg != null) {
            ModelAndView mv = new ModelAndView("redirect:login");
          
               NotificationUtil.addNotificationMessage(errorMsg);
                             
            return mv;
        } else {
            String nextPage = loginService.getPageAfterLogin(user);
            if(nextPage != null){
                return new ModelAndView("redirect:"+nextPage);
            }else{
                return new ModelAndView("redirect:user/"+user.getUserName());
            }
        }
    }

    
   
    
    /**
     * 
     * @param password required=false because we don't use pswd in DEV
     * @param keepLoggedIn required=false because when user don't check the checkbox we get a 400 error
     * @return
     * 
     * used with login.js
     * Actualy not used because we dedicated a signin page
     */
    /*
    @RequestMapping(value="ajax/loginsubmit",method=RequestMethod.POST)
    public ResponseEntity<String> loginSubmitAjax(@RequestParam("identifier") String userNameOrMail,
            @RequestParam(value="password",required=false) String password,
            @RequestParam(value="keepLoggedIn",required=false) boolean keepLoggedIn) {

        
        String errorMsg = null;
        User user = null;
        try {
            user = loginService.login(userNameOrMail, password, keepLoggedIn,null);


        } catch (UserNotFoundException e) {
            errorMsg="L'utilisateur '"+userNameOrMail+"' n'existe pas";

        } catch (InvalidPasswordException e) {
            errorMsg="Ce mot de passe n'est pas valide pour l'utilisateur '"+userNameOrMail+"'";

        } catch (UserNotValidatedException e) {
            errorMsg="L'utilisateur '"+userNameOrMail+"' n'a pas été valide. Vérifiez vos mails reçus et cliquez sur le lien du mail qui vous a été envoyé à l'enregistrement.";

        } catch (UserLockedException e) {
            errorMsg="L'utilisateur  '"+userNameOrMail+"' est verrouillé. Contacter un administrateur pour le déverrouiller.";

        } catch (WaitDelayNotReachedException e) {
            errorMsg="Suite à de multiples tentatives de login échouées, votre utilisateur s'est vu imposé un délai d'attente avant de pouvoir se relogguer, ceci pour des raisons de sécurité." +
                    " Actuellement, il reste "+ DateUtil.formatIntervalFromToNow(e.getNextPossibleTry()) +" à attendre.";
        }
       catch(SocialAccountAlreadyExistException e){
            
            errorMsg="Vous possédez déjà un compte actif sur "+ContextUtil.servletContext.getAttribute("website_name")+" associé à un compte social(Facebook,Google+,LinkedIn.....) avec ce nom d'utilisateur";
            
        }

        
        //ResponseEntity<String> response = new ResponseEntity<String>(errorMsg, null);
        
        if (errorMsg != null) {
         //   ModelAndView mv = new ModelAndView("login");
         //   this.setMessage(mv, errorMsg);
            return new ResponseEntity<String>(errorMsg, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<String>("ok", HttpStatus.OK);
        }
    }
    
    @RequestMapping("/ajax/login")
    public String login() {
        return "login";
    }

*/
    /*
     * called from login.jsp when the user check the autologin checkbox
     * next these value will be used in the spring social signin adapter
     */
    @RequestMapping(value="ajax/autologin")
    public ResponseEntity<String> loginSubmitAjax(@RequestParam("autologin") boolean autologin,HttpServletRequest request){
        request.getSession().setAttribute(AUTOLOGIN_KEY, new Boolean(autologin));          
        return new ResponseEntity<String>(HttpStatus.OK);
    }
  
    
   
}
