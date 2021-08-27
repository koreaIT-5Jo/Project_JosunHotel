package com.josun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/review")
public class ReivewController {
	
	@RequestMapping(value = "main")
	public String main() {
		
		return "reviewMain";
	}
	

}
