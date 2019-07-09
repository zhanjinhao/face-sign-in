package cn.facesignin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class UiController {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@RequestMapping("/login")
	public String login() throws Exception{
		amqpTemplate.convertAndSend("mq.exChange", "mq.test.send", "hello world!");
		return "login";
	}
	@RequestMapping("/uregister")
	public String uregister() throws Exception{
		return "uregister";
	}
	@RequestMapping("/oregister")
	public String oregister() throws Exception{
		return "oregister";
	}
	@RequestMapping("/admin")
	public String admin() throws Exception{
		return "admin";
	}
	@RequestMapping("/user")
	public String user() throws Exception{
		return "user";
	}
	@RequestMapping("/activity")
	public String activity() throws Exception{
		return "activity";
	}
	@RequestMapping("/organization")
	public String organization(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "organization";
	}
	@RequestMapping("/verify")
	public String verify() throws Exception {
		return "verify";
	}
	@RequestMapping("/error")
	public String error() throws Exception{
		return "error";
	}
	@RequestMapping("/mobileVerify")
	public String mobileVerify() throws Exception{
		return "mobileVerify";
	}
	@RequestMapping("/testmq")
	public void Testmq() {
		amqpTemplate.convertAndSend("mq.asdfExChange", "mq.asdf.send", "hello world!");
	}
}