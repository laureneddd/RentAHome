package com.example.rentahome.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.rentahome.entity.User;
import com.example.rentahome.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
	UserService userService;

	@GetMapping("/signup_page")
	public String signupPage() {
		
		System.out.println("inside signupPage()");
		
		return "signup_page";
	}


    @PostMapping("/login")
	public ModelAndView login(String name, String password, HttpServletRequest request) {
		ModelAndView modelAndView;
		User user = userService.login(name, password);
		System.out.println(user);
		if(user != null){
			HttpSession session = request.getSession();
			modelAndView = new ModelAndView("welcome");
			session.setAttribute("loggedInUser", user);
		}
		else{
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("loginFailedStatus", "Login Failed, Please Try Again!!");
		}
		return modelAndView;
	}


		@GetMapping("/delete/{userId}")
		public ModelAndView deleteUser(@PathVariable int userId) {

			userService.deleteUser(userId);

			ModelAndView modelAndView = new ModelAndView("update_user");

			return modelAndView;
		}

		@GetMapping("/update_page/{userId}")
		public ModelAndView updateUserPage(@PathVariable int userId) {

			User user = userService.findByUserId(userId);

			userService.updateUser(user);

			ModelAndView modelAndView = new ModelAndView("update_user");

			modelAndView.addObject("user", user);

			return modelAndView;
		}

		@GetMapping("/logout")
		public ModelAndView logout(HttpServletRequest request) {
			
			System.out.println("inside logout...");
			
			HttpSession session = request.getSession(false);
			System.out.println(((User)session.getAttribute("loggedInUser")).getName());
			System.out.println("session id while logout is "+session.getId());
			
			session.invalidate();
			
			ModelAndView modelAndView = new ModelAndView("index");
			
			return modelAndView;
		}
    
}
