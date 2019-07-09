package cn.facesignin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefalutUIController {
	
	@RequestMapping("/")
	public String login() throws Exception{
		return "redirect:/ui/login.action";
	}
	
}