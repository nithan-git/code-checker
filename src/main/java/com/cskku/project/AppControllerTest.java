package com.cskku.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class AppControllerTest {


	@GetMapping("/")
	public String defaultPage() {
		return "";
	}
	
	

}
