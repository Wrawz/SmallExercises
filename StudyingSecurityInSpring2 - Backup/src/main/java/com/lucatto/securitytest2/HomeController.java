package com.lucatto.securitytest2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
//	@Autowired
//	UserDAO userDAO;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/home")
	public String home2() {
		return home();
	}
}
