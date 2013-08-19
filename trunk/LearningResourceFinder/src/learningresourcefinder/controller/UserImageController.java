package learningresourcefinder.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import learningresourcefinder.model.User;
import learningresourcefinder.repository.UserRepository;
import learningresourcefinder.security.Privilege;
import learningresourcefinder.security.SecurityContext;
//import learningresourcefinder.service.BadgeService;
import learningresourcefinder.service.UserService;
import learningresourcefinder.util.CurrentEnvironment;
import learningresourcefinder.util.FileUtil;
import learningresourcefinder.util.FileUtil.InvalidImageFileException;
import learningresourcefinder.util.ImageUtil;
import learningresourcefinder.util.NotificationUtil;

@Controller
@RequestMapping("/user")
public class UserImageController extends BaseController<User> {

	@Autowired UserRepository userRepository;
	@Autowired UsersConnectionRepository usersConnectionRepository;
	@Autowired UserService userService;
//	@Autowired BadgeService badgeService;
	@Autowired CurrentEnvironment currentEnvironment;
	
	@RequestMapping("/image")
	public ModelAndView userImage(@RequestParam("id") long userid){
		User user = getRequiredEntity(userid);
		ModelAndView mv= new ModelAndView("userimage", "user", user);
		
		return mv;
	}

	@RequestMapping("/imageadd")
	public ModelAndView userImageAdd(@RequestParam("id") long userid,
			@RequestParam("file") MultipartFile multipartFile) throws Exception{
		
		User user = getRequiredEntity(userid);

		SecurityContext.assertCurrentUserMayEditThisUser(user);
		
		///// Save original image, scale it and save the resized image.
		try {
			FileUtil.uploadFile(multipartFile, FileUtil.getGenFolderPath(currentEnvironment) + FileUtil.USER_SUB_FOLDER + FileUtil.USER_ORIGINAL_SUB_FOLDER, 
					FileUtil.assembleImageFileNameWithCorrectExtention(multipartFile, Long.toString(user.getId())));

			BufferedImage resizedImage = ImageUtil.scale(new ByteArrayInputStream(multipartFile.getBytes()),120 * 200, 200, 200);
						
			ImageUtil.saveImageToFileAsJPEG(resizedImage,  
					FileUtil.getGenFolderPath(currentEnvironment) + FileUtil.USER_SUB_FOLDER + FileUtil.USER_RESIZED_SUB_FOLDER +  FileUtil.USER_RESIZED_LARGE_SUB_FOLDER, user.getId() + ".jpg", 0.9f);
			
			BufferedImage resizedSmallImage = ImageUtil.scale(new ByteArrayInputStream(multipartFile.getBytes()),40 * 40, 60, 60);
			
			ImageUtil.saveImageToFileAsJPEG(resizedSmallImage,  
					FileUtil.getGenFolderPath(currentEnvironment) + FileUtil.USER_SUB_FOLDER + FileUtil.USER_RESIZED_SUB_FOLDER + FileUtil.USER_RESIZED_SMALL_SUB_FOLDER, user.getId() + ".jpg", 0.9f);

			user.setPicture(true);
			
			userRepository.merge(user);
			
//			badgeService.grantIfUserIsComplete(user);
			
		} catch (InvalidImageFileException e) {  //Tell the user that its image is invalid.
			NotificationUtil.addNotificationMessage(e.getMessageToUser());
		}


		ModelAndView mv = new ModelAndView("redirect:/user/" + user.getUserName());
		mv.addObject("random", System.currentTimeMillis());

		return mv;
	}

	 @RequestMapping("/imagedelete")
	 public ModelAndView userImageDelete(@RequestParam("id") long userid){
		 User user = getRequiredEntity(userid);
//
//		 FileUtil.deleteFilesWithPattern(FileUtil.getGenFolderPath() + FileUtil.USER_SUB_FOLDER + FileUtil.USER_ORIGINAL_SUB_FOLDER, user.getId()+".*");
//		 FileUtil.deleteFilesWithPattern(FileUtil.getGenFolderPath() + FileUtil.USER_SUB_FOLDER + FileUtil.USER_RESIZED_SUB_FOLDER + FileUtil.USER_RESIZED_LARGE_SUB_FOLDER, user.getId()+".*");
//		 FileUtil.deleteFilesWithPattern(FileUtil.getGenFolderPath() + FileUtil.USER_SUB_FOLDER + FileUtil.USER_RESIZED_SUB_FOLDER +  FileUtil.USER_RESIZED_SMALL_SUB_FOLDER, user.getId()+".*");
//		 
//		 user.setPicture(false);
		 userService.userImageDelete(user);
		 userRepository.merge(user);

		 return new ModelAndView("redirect:"+user.getUserName());
		 
	 }

	 private boolean canEdit(User user) {
		 return user.equals(SecurityContext.getUser()) || SecurityContext.isUserHasPrivilege(Privilege.MANAGE_USERS);
	 }

	 
	
}
