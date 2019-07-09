package cn.facesignin.Interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.facesignin.pojo.Organization;
import cn.facesignin.pojo.User;
import cn.facesignin.service.OrgService;
import cn.facesignin.service.UserService;

/**
 * 非自动放行的都要自动拦截...
 * @author ISJINHAO
 *
 */
public class AutoLoginInterceptor implements HandlerInterceptor{

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		HttpSession session = req.getSession();
		
		Object ouser = session.getAttribute("user");
		Object oorg = session.getAttribute("org");
		
		if(ouser != null || oorg != null) {
			return true;
		}
		
		Cookie[] cookies = req.getCookies();
		String userId = null;
		String upwd = null;
		String oemail = null;
		String opwd = null;
		// 遍历Cookie获取关于用户信息的Cookie值
		if (cookies != null) {
			for (Cookie eCookie : cookies) {
				if (eCookie.getName().equals("cookieUid"))
					userId = eCookie.getValue();
				if (eCookie.getName().equals("cookieUpwd"))
					upwd = eCookie.getValue();
				if (eCookie.getName().equals("cookieOemail"))
					oemail = eCookie.getValue();
				if (eCookie.getName().equals("cookieOpwd"))
					opwd = eCookie.getValue();
			}
		}
		System.out.println("user  ==>   " + userId + "  " + upwd);
		System.out.println("org   ==>   " + oemail + "  " + opwd);
		// 存在用户信息的Cookie值时自动登录
		if (userId != null && upwd != null) {
			User user = userService.selectUserByUid(userId);
			if(user != null && user.getUpwd().equals(upwd))
				session.setAttribute("user", user);
		}
		if (oemail != null && opwd != null) {
			Organization org = orgService.selectOrgByEmail(oemail);
			if(org != null && org.getOpwd().equals(opwd))
				session.setAttribute("org", org);
		}
		return true;
	}

}
