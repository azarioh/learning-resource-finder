package learningresourcefinder.controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import learningresourcefinder.model.User;
import learningresourcefinder.model.User.Role;
import learningresourcefinder.repository.UserRepository;
import learningresourcefinder.security.Privilege;
import learningresourcefinder.security.SecurityContext;

/** To edit the role and privileges of a user */
@Controller
@RequestMapping("/user")
public class PrivilegeController extends BaseController<User> {
	
	// Stores 3 values for each privilege. It eases the job of the JSP putting these values in a table.
	public class PrivilegeTriplet{
		public boolean permitted = false;
		public Privilege privilege;
		public Role role;

		public boolean isPermitted() {
			return permitted;
		}
		public void setPermitted(boolean permitted) {
			this.permitted = permitted;
		}
		public Privilege getPrivilege() {
			return privilege;
		}
		public void setPrivilege(Privilege privilege) {
			this.privilege = privilege;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}

	}
	
	@Autowired 	UserRepository userRepository;
		
	@RequestMapping("/privilegeedit")
	public ModelAndView privilegeEdit(@RequestParam("id") Long userId) {
		SecurityContext.assertUserHasPrivilege(Privilege.MANAGE_USERS);
		User user = userRepository.find(userId);
		return createModelAndView(user);
	}
		
	@RequestMapping("/roleeditsubmit")
	public ModelAndView roleEditSubmit(@RequestParam("role")String role,@RequestParam("id") Long userId) {
		SecurityContext.assertUserHasPrivilege(Privilege.MANAGE_USERS);
		User user = userRepository.find(userId);
		user.setRole(Role.valueOf(role));
		return createModelAndView(user);
	}

	@RequestMapping("/privilegeeditsubmit")
	public ModelAndView privilegeEditSubmit(@RequestParam Map <String, String> params){
		SecurityContext.assertUserHasPrivilege(Privilege.MANAGE_USERS);
		User user = getRequiredEntity(Long.parseLong(params.get("id")));
		//ModelAndView mv = new ModelAndView("redirect:user", "username", user.getUserName());
		ModelAndView mv = new ModelAndView("redirect:/user/"+user.getUserName());
		params.remove("id");
		user.getPrivileges().clear();		
		for (Map.Entry<String, String> entry: params.entrySet()) {
			try {
				user.getPrivileges().add(Privilege.valueOf(entry.getKey()));
			} catch (Exception e) {
				throw new IllegalArgumentException("parameters should only contains Id and privileges");
			}
		}
		// em.merge(user); Not needed (we did not modify the user, we changed the .privilege collection). Save will happen with dirty checking.
		return mv;
	}
	
	private ModelAndView createModelAndView(User user) {
		ModelAndView mv =new ModelAndView("privilege");
		mv.addObject("user", user);
		
		// Create triplet list
		List<PrivilegeTriplet> triplets = new ArrayList<PrivilegeTriplet>();
		EnumSet<Privilege> usersprivilege = SecurityContext.getAllAssociatedPrivileges(user);
		for (Privilege privilege : Privilege.values()) {
			PrivilegeTriplet newTriplet = new PrivilegeTriplet();
			newTriplet.setPrivilege(privilege);
			if (usersprivilege.contains(privilege)) {
				newTriplet.setPermitted(true);
			}
			newTriplet.setRole(privilege.getAssociatedRole());
			triplets.add(newTriplet);
		}
		mv.addObject("privilegetriplets", triplets);
		return mv;
	}
	

}
