package cn.facesignin.controller;

import static org.hamcrest.CoreMatchers.sameInstance;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.facesignin.service.VerifyService;

@Controller
@RequestMapping("/ui")
public class UiController {
	
	@RequestMapping("/login")
	public String login() throws Exception{
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
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private VerifyService verifyService;
	
	@RequestMapping("/mobileVerify")
	public String mobileVerify(String aid, String admin) throws Exception{
		
		String strAid = aid + "`" + admin;
		
		if(redisTemplate.hasKey(strAid)) {
			
			Map<Object, Object> entries = redisTemplate.opsForHash().entries(strAid);
			
			verifyService.saveVerifyRecordToDB(entries);
			
			redisTemplate.delete(strAid);
		}
		
		Object object = redisTemplate.opsForValue().get(aid);
		if(object == null) {
			redisTemplate.opsForValue().set(aid, 1);
		}else
			redisTemplate.opsForValue().increment(aid, 1);
		
		return "mobileVerify";
	}
}