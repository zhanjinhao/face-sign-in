package cn.facesignin.controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.facesignin.constant.ThreadPoolConfig;
import cn.facesignin.thread.SendEmailCode;
import cn.facesignin.utils.VerifyCodeUtils;

@Controller
@RequestMapping("/utils")
public class UtilsController {
	
	private ExecutorService emailThreadPool = Executors.newFixedThreadPool(ThreadPoolConfig.EMAIL);
	
	@RequestMapping("/verCode")
	public void verCode(HttpServletResponse response, HttpServletRequest request, Integer height, Integer width){
		String verCode = VerifyCodeUtils.generateVerifyCode(4);
		//获得一个session对象
		HttpSession session = request.getSession();
		
		//删除之前正确的验证码（如果有的话），并且设置为当前正确的验证码
		session.removeAttribute("rightVerCode");
		session.setAttribute("rightVerCode", verCode);
		
		System.out.println("login Vercode  ==>  " + verCode);
		
		//生成一个100px * 46px像素的验证码图片
		try {
			VerifyCodeUtils.outputImage(width, height, response.getOutputStream(), verCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sendEmailVerCode")
	@ResponseBody
	public void sendEmailVerCode(String email, String type, HttpServletRequest request) {
		
		String verCode = VerifyCodeUtils.generateVerifyCode(4);
		String msg = "你的验证码是:  "+ verCode;
		//获得一个session对象
		HttpSession session = request.getSession();
		if("org".equals(type)) {
			session.removeAttribute("oemailVerCode");
			session.setAttribute("oemailVerCode" + email, verCode);
		}else if("user".equals(type)) {
			session.removeAttribute("uemailVerCode");
			session.setAttribute("uemailVerCode" + email, verCode);
			System.out.println("uemailVerCode" + email + " ==> " + verCode);
			
		}else if("newpwd".equals(type)) {
			session.removeAttribute("newpwdVerCode");
			session.setAttribute("newpwdVerCode" + email, verCode);
			System.out.println("newpwdVerCode" + email + " ==> " + verCode);
		}else if("newEmailVercode".equals(type)) {
			session.removeAttribute("newEmailVercode");
			session.setAttribute("newEmailVercode" + email, VerifyCodeUtils.generateVerifyCode(6));
			System.out.println("newEmailVercode" + email + " ==> " + VerifyCodeUtils.generateVerifyCode(6));
		}else if("oldEmailVercode".equals(type)) {
			session.removeAttribute("oldEmailVercode");
			session.setAttribute("oldEmailVercode" + email, verCode);
			System.out.println("oldEmailVercode" + email + " ==> " + verCode);
		}else
			return;
		try {
			emailThreadPool.execute(new SendEmailCode(msg, email));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
