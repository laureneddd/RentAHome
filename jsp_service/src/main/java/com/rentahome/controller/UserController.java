package com.rentahome.controller;


import com.rentahome.dto.FeatureDTO;
import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.ReservationDTO;
import com.rentahome.dto.UserDTO;
import com.rentahome.entity.User;
import com.rentahome.service.PropertyService;
import com.rentahome.service.ReservationService;
import com.rentahome.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@RequestMapping("/user")
public class UserController {
    @Autowired
	UserService userService;

	@Autowired
	PropertyService propertyService;

	@Autowired
	ReservationService reservationService;

	@GetMapping("/signup_page")
	public ModelAndView signupPage() {

		System.out.println("inside signupPage()");
		
		return new ModelAndView("signup_page");
	}

	@PostMapping("/register")
	public ModelAndView registerUser(User user) {
		
		System.out.println("inside register()..."+user.getName()+", "+user.getPassword()+", "+user.getRole()+", "+user.getEmail());
		
		userService.addUser(user);
		
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("registerSuccess", "Registration Successfull, Now Please Login!!");
		
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		
		System.out.println("inside signupPage()");
		
		return new ModelAndView("login");
	}

    @PostMapping("/user_login")
	public ModelAndView login(HttpServletRequest request) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println(name);
		ModelAndView modelAndView;
		UserDTO user = userService.login(name, password);
		System.out.println(user);
		if(user != null){
			HttpSession session = request.getSession();
			if(user.getRole().equals("Owner")){
				List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(user.getUserId());
				List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(user.getUserId());
				modelAndView = new ModelAndView("OwnerDashboard");
				modelAndView.addObject("ownerId", user.getUserId());
				modelAndView.addObject("propertyDTOS", propertyDTOS);
				modelAndView.addObject("reservationDTOS", reservationDTOS);
			}else {
				modelAndView = new ModelAndView("index");
				session.setAttribute("loggedInUser", user);
				modelAndView.addObject("searchResult", false);
			}
		}
		else{
			modelAndView = new ModelAndView("login");
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

			UserDTO user = userService.findByUserId(userId);

			//userService.updateUser(user);

			ModelAndView modelAndView = new ModelAndView("update_page");

			modelAndView.addObject("user", user);

			return modelAndView;
		}

		@PostMapping("/update")
		public ModelAndView updateUser(@RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam String role) throws InterruptedException {
			String updateStatus = userService.updateUser(name, password, email, role);
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("updateMsg", updateStatus);
			return modelAndView;
		}


		@GetMapping("/logout")
		public ModelAndView logout(HttpServletRequest request) {
			
			System.out.println("inside logout...");
			
			// Retrieve the current session, but don't create a new one if it doesn't exist
			HttpSession session = request.getSession(false);

			if (session != null) {
				// Retrieve the user from the session
				UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");
		
				if (loggedInUser != null) {
					// Log the user's name if available
					System.out.println("User logging out: " + loggedInUser.getName());
				} else {
					// Handle the case where the user is not logged in
					System.out.println("No user logged in");
				}
		
				System.out.println("Session ID while logout is " + session.getId());
				
				// Invalidate the session
				session.invalidate();
			} else {
				System.out.println("No session found");
			}
			
			ModelAndView modelAndView = new ModelAndView("index");
			modelAndView.addObject("searchResult", false);
			
			return modelAndView;
		}

		@GetMapping("/booking")// "/" represents the very first page of your application 
		public ModelAndView bookingPage() {
			
			System.out.println("inside bookingPage()");
			
			return new ModelAndView("booking");//This String "index" is supposed to be the name of the html/jsp name. Do not expect
							//"index" as a string to be returned form the server as a plain text...
		}
    
}
