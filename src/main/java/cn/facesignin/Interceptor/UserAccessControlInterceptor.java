package cn.facesignin.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.facesignin.pojo.User;
import cn.facesignin.utils.InterceptorUtils;

public class UserAccessControlInterceptor implements HandlerInterceptor{
	
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	/**
	 * 拦截所有的请求，只有存在用户的Session才放行至相应界面
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		HttpSession session = req.getSession();
		
		//直接放行的请求
		if(InterceptorUtils.isReleaseDirect(req.getRequestURI()))
			return true;
		
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			res.sendRedirect(req.getContextPath() + "/ui/login.action");
			return false;
		}
		return true;
	}


	
}
