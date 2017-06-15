package com.katalyst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApparelMagicController {
	
@RequestMapping("/")
public String angular(Model model){
	return "index";
}

}