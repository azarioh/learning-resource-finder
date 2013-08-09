package learningresourcefinder.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import learningresourcefinder.mail.MailingDelayType;
import learningresourcefinder.model.User;
import learningresourcefinder.model.User.AccountStatus;
import learningresourcefinder.model.User.Gender;
import learningresourcefinder.repository.UserRepository;
import learningresourcefinder.security.Privilege;
import learningresourcefinder.security.SecurityContext;
import learningresourcefinder.service.IndexManagerService;
import learningresourcefinder.service.UserService;
import learningresourcefinder.util.DateUtil;
import learningresourcefinder.util.HTMLUtil;
import learningresourcefinder.util.NotificationUtil;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/user")
public class UserEditController extends BaseController<User> {
    
    @Autowired UserRepository userRepository;
    @Autowired UserService userService; 
    //@Autowired BadgeService badgeService;
    @Autowired  IndexManagerService indexManagerService;
    @RequestMapping("/edit")
    public ModelAndView userEdit(@RequestParam(value="id", required=true) long userId) {
        
        User user = getRequiredEntity(userId); 
    	SecurityContext.assertCurrentUserMayEditThisUser(user);
               
    	ModelAndView mv=prepareModelAndView(userId, user);
		
    	return mv;
    }
    
    private ModelAndView prepareModelAndView(Long userId,User user) { 
    	ModelAndView mv=new ModelAndView("useredit");
    	// Sets an initial date in the form
        
        bootBirthdayInMv(user, mv);
        
    	mv.addObject("id", userId);  // Do not remove because when we use the dummy model attribute (doNotUseThisUserInstance), it has no id. 
    	mv.addObject("user", user);
    	mv.addObject("canChangeUserName", (SecurityContext.canCurrentUserChangeUser(getRequiredEntity(userId)) /// If we have a validation error, the given user is a fake, used for error handling;
    												/*&& getRequiredEntity(userId).getCertificationDate() == null*/)// in that case, we have to compare the current user with the real one, hence the getRequiredEntity
    										|| SecurityContext.isUserHasPrivilege(Privilege.MANAGE_USERS));
    	
    	List<AccountStatus> statusList = new ArrayList<AccountStatus>();
    	
    	// User.Status (ACTIVE, LOCKED, etc.)
    	for(AccountStatus status : AccountStatus.values()){
    	    statusList.add(status);   	    
    	}
    	mv.addObject("statusList", statusList);
    	User currentUser = SecurityContext.getUser();
    	mv.addObject("canChangeAccountStatus", !currentUser.equals(getRequiredEntity(userId)) && SecurityContext.isUserHasPrivilege(Privilege.MANAGE_USERS));
    	
    	return mv;
    }

	private void bootBirthdayInMv(User user, ModelAndView mv) {
		if (user.getBirthDate()!=null) {
			Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(user.getBirthDate());
            mv.addObject("birthDay", birthCalendar.get(Calendar.DAY_OF_MONTH));
            mv.addObject("birthMonth", birthCalendar.get(Calendar.MONTH));
            mv.addObject("birthYear", birthCalendar.get(Calendar.YEAR));
        }
	}
    
    @RequestMapping("/delete")
    public ModelAndView userDelete(@RequestParam(value="id") Long idUser){
        User user  = userRepository.find(idUser);
        SecurityContext.assertUserHasPrivilege(Privilege.DELETE);
        return getConfirmBeforeDeletePage(user.getUserName(), "/user/deleteconfirmed", "/user/"+user.getUserName(), idUser);

    }

    @RequestMapping("/deleteconfirmed")
    public ModelAndView userDeleteConfirmed(@RequestParam(value="id") Long idUser){
        User user  = userRepository.find(idUser);
        SecurityContext.assertUserHasPrivilege(Privilege.DELETE);
        //userService.setUser(user);
        NotificationUtil.addNotificationMessage("L'utilisateur est bien supprimé");
        return new ModelAndView("home");
    }
    
    
    
    @RequestMapping("/editsubmit")
    public ModelAndView userEditSubmit(@RequestParam(value="lastName",required=false) String newLastName,
                                       @RequestParam(value="firstName",required=false) String newFirstName,
                                       @RequestParam(value="userName") String newUserName,
                                       @RequestParam(value="gender",required=false) Gender newGender,
                                       @RequestParam(value="mail", required=false) String newMail,
                                       @RequestParam(value="birthDay") String day,
                                       @RequestParam(value="birthMonth") String month,
                                       @RequestParam(value="birthYear") String year,
                                       @RequestParam(value="nlSubscriber", required=false) Boolean newNlSubscriber,
                                       @RequestParam(value="title") String title,
                                       @RequestParam(value="certified", required=false) Boolean certified,
                                       @RequestParam(value="mailingDelayType",required=false) MailingDelayType mailingDelayType,
                                       @RequestParam(value="accountStatus",required=false) AccountStatus accountStatus,
                                       @RequestParam("id") long userId,
                                       @Valid @ModelAttribute User doNotUseThisUserInstance,  // To enable the use of errors param.
                                       Errors errors) {
    	
        User user = getRequiredEntity(userId); 
    	SecurityContext.assertCurrentUserMayEditThisUser(user);
        
    	//field html dangerousity check
    	checkFieldContainDangerousHtml(newLastName,"lastName", errors);
    	checkFieldContainDangerousHtml(newFirstName,"firstName", errors);
    	checkFieldContainDangerousHtml(newUserName,"userName", errors);  
    	checkFieldContainDangerousHtml(newMail,"mail", errors);
    	checkFieldContainDangerousHtml(title,"title", errors);

       
        //birthDate
    	Date dateNaiss = null;
    	// Verification 1: is the date in the future?
        if ((!day.equals("null"))&&(!month.equals("null"))&&(!year.equals("null"))) {
			dateNaiss = DateUtil.parseyyyyMMdd(year + "-" + month + "-" + day);
			if (dateNaiss.after(new Date())) {
				////constrcut modelandview because brithday is split in 3 input so we connat use the erros variable.
				ModelAndView mv = prepareModelAndView(userId, user);
				mv.addObject("errorBirthDate", "Vous avez sélectionné une date dans le futur. Veuillez choisir une date de naissance passée.");
				return mv;
			}
        }  // No else because the birthdate is not mandatory. 
        
        
		// userName
        boolean hasUserAlreadyExist=false;
        newUserName = org.springframework.util.StringUtils.trimAllWhitespace(newUserName).toLowerCase();  // remove blanks
        if (! org.apache.commons.lang3.StringUtils.equalsIgnoreCase(user.getUserName(), newUserName)) {  // Want to change username
        	// check duplicate
            User otherUser = userRepository.getUserByUserName(newUserName);
            if (otherUser != null) { // Another user already uses that userName
                errors.rejectValue("userName", null, "Ce pseudonyme est déjà utilisé par un autre utilisateur.");
                hasUserAlreadyExist=true;
            }
        }
        
        // e-mail
        if(newMail != null){
        	newMail = org.springframework.util.StringUtils.trimAllWhitespace(newMail).toLowerCase();   // remove blanks
	        if (! org.apache.commons.lang3.StringUtils.equalsIgnoreCase(user.getMail(), newMail)) {  // user wants to change e-mail
	        	// check duplicate
	            User otherUser = userRepository.getUserByEmail(newMail);
	            if (otherUser != null) {
	                errors.rejectValue("mail", null, "Ce mail est déjà utilisé par un autre utilisateur.");
	            }
	        }
        } else {  // No mail provided
        	// It's ok, mail is not mandatory...
        }
       
        
        
        if (errors.hasErrors()) {
            ModelAndView mv = prepareModelAndView(userId, doNotUseThisUserInstance);
            if (doNotUseThisUserInstance.getUserName()==""|| hasUserAlreadyExist==true ) {
                doNotUseThisUserInstance.setUserName(user.getUserName());  // We need to restore the username, because the "Cancel" link in the JSP needs it.
            }
            Calendar birthCalendar = Calendar.getInstance();
          
            bootBirthdayInMv(user, mv);
            return mv;
        }
        
        // We start modifiying user (that may then be automatically saved by hibernate due to dirty checking.
        if ((SecurityContext.isUserHasPrivilege(Privilege.MANAGE_USERS) || certified == null || certified ==  false ) &&
        	( !ObjectUtils.equals(newFirstName, user.getFirstName()) || !ObjectUtils.equals(newLastName, user.getLastName()) || !ObjectUtils.equals(newUserName, user.getUserName()))){
           //userService.changeUserName(user, newUserName, newFirstName, newLastName); 
        }
        
        // MailDelayType
        if(mailingDelayType != null){
            user.setMailingDelayType(mailingDelayType);
        }
        
        user.setBirthDate(dateNaiss);
        user.setMail(newMail);
        user.setGender(newGender);
        user.setNlSubscriber(newNlSubscriber != null ? newNlSubscriber : false);
        user.setTitle(title);
       /* if (SecurityContext.isUserHasPrivilege(Privilege.MANAGE_USERS)) {  // User sees the check box
            if ((certified == null || certified ==  false)) {  // Check box not in the request (=> unchecked)
            	user.setCertificationDate(null);
            } else if (user.getCertificationDate() == null) {  // User is not yet certified
            	user.setCertificationDate(new Date());
            }
        	
        }*/
        user.setAccountStatus(accountStatus);
        user = userRepository.merge(user);
        // TODO: indexManagerService.update(user);
        //badgeService.grantIfUserIsComplete(user);
        
        return new ModelAndView("redirect:/user/"+user.getUserName());
    }

	private void checkFieldContainDangerousHtml(String toCheck,String fieldName, Errors errors) {
		if( !HTMLUtil.isHtmlSecure(toCheck)) {
			errors.rejectValue(fieldName, null, "vous avez introduit du HTML/Javascript dans vos informations " + toCheck);
		}
	}
}
