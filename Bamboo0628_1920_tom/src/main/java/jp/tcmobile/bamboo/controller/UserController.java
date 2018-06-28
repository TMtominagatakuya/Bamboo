package jp.tcmobile.bamboo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.tcmobile.bamboo.model.Authorization;
import jp.tcmobile.bamboo.model.Authorization.Role;
import jp.tcmobile.bamboo.model.User;
import jp.tcmobile.bamboo.repository.UserRepository;
import jp.tcmobile.bamboo.service.TestServiceImpl;
import jp.tcmobile.bamboo.service.UserDetail;
import jp.tcmobile.bamboo.service.UsersServiceImpl;

@Controller
public class UserController {

	@Autowired
	TestServiceImpl testServiceImpl;
	
	@Autowired
	UsersServiceImpl usersServiceImpl;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping("/index")
	public ModelAndView showHome( @AuthenticationPrincipal UserDetail userDetail){
	    User user =userRepository.findByAcountName(userDetail.getUsername());
	    ModelAndView mav;
	    boolean isAdmin = userDetail.getAuthorities().toString().contains(Role.admin.toString());
	    boolean isStaff = userDetail.getAuthorities().toString().contains(Role.staff.toString());
	        if(isAdmin) {
	            mav = new ModelAndView("redirect:test/testlist/category_all/page1");
	            
	        }
	        else if(isStaff) {
	            mav = new ModelAndView ("redirect:/article/category_all/page1");
	        }
	        else {
	            //後で失敗用のビューを追加
	        	mav = new ModelAndView();
	            mav.setViewName("error");
	        }
	    return mav;
	}
	
	// ユーザ一覧画面
	@GetMapping("/users/userslist")
	public ModelAndView showUsersList(ModelAndView mav) {
		mav.setViewName("users/userslist");
		List<User> userlist = userRepository.findAll();
		mav.addObject("userlist", userlist);
		return mav;
	}
	
	// ユーザ新規登録画面
		@GetMapping("/users/usersadd")
		public ModelAndView showUsersAdd(ModelAndView mav) {
			mav.setViewName("users/usersadd");
			User user = new User();
			mav.addObject(user);
			return mav;
		}
		
		@PostMapping(value= "/users/usersadd")
		public ModelAndView saveUsers(@ModelAttribute("user") @Validated User user, BindingResult result, ModelAndView mav) {
			if(!result.hasErrors()) {
				usersServiceImpl.saveTest(user);
				mav.setViewName("/users/userslist");
			} else {
				mav.addObject("msg", "sorry, error is occured...");
			}
			return mav;
		}
		
	// ユーザ―編集画面
//		@GetMapping("/usersedit")
//		public ModelAndView showUsersEdit(ModelAndView mav) {
//			mav.setViewName("users/usersedit");
//			return mav;
//		}

}