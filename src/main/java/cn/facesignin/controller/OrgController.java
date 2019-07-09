package cn.facesignin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.facesignin.entity.PageResult;
import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.Organization;
import cn.facesignin.pojo.controller.OrgAdminsPojo;
import cn.facesignin.service.ActivityService;
import cn.facesignin.service.MyGroupService;
import cn.facesignin.service.OrgService;

@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private OrgService orgService;
	
	@Autowired
	private MyGroupService groupService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/checkOemail")
	@ResponseBody
	public Map checkUemail(String email) {
		Map map = new HashMap<>();
		Organization org = orgService.selectOrgByEmail(email);
		if (org == null) {
			map.put("info", true);
		} else
			map.put("info", false);
		return map;
	}

	@RequestMapping("/getOrgName")
	@ResponseBody
	public Map getOrgName(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Organization org = (Organization) session.getAttribute("org");
		Map map = new HashMap<>();
		map.put("info", org.getOname());
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public Map register(@RequestBody Organization org, HttpServletRequest request) {
		//org的oowner传输邮箱验证码
		Map map = new HashMap<>();

		HttpSession session = request.getSession();
		String uemailVerCode = (String)session.getAttribute("oemailVerCode" + org.getOemail());
		
		//判断验证码
		if(uemailVerCode == null || !uemailVerCode.equals(org.getOowner())) {
			map.put("info", "验证码错误！");
			return map;
		}
		org.setOowner(null);
		org.setOcreateTime(new Date());
		Integer id = orgService.insertOrg(org);
		
		map.put("oid", id);
		map.put("info", "注册成功，正在跳往登录界面！");
		session.removeAttribute("oemailVerCode" + org.getOemail());
		return map;
	}
	@RequestMapping("/login")
	@ResponseBody
	public Map login(@RequestBody Organization org, HttpServletRequest request, HttpServletResponse response) {
		Map map = new HashMap<>();
		HttpSession session = request.getSession();
		String rightVerCode = (String) session.getAttribute("rightVerCode");
		if(rightVerCode == null || !rightVerCode.equals(org.getOname())) {
			map.put("info", "验证码错误！");
			return map;
		}
		Organization orgFromDB = orgService.selectOrgByEmail(org.getOemail());
		if(orgFromDB == null || !orgFromDB.getOpwd().equals(org.getOpwd())) {
			map.put("info", "账户或密码错误！");
			return map;
		}
		
		session.setAttribute("org", orgFromDB);
		
		//创建Cookie
		Cookie cookieUsername = new Cookie("cookieOemail", orgFromDB.getOemail());
		Cookie cookiePassword = new Cookie("cookieOpwd", orgFromDB.getOpwd());
		//持久化时间为31天
		cookiePassword.setMaxAge(60*60*24*31);
		cookieUsername.setMaxAge(60*60*24*31);
		//设置cookie的携带路径，访问WEB应用所有的资源都携带此Cookie
		cookieUsername.setPath(request.getContextPath());
		cookiePassword.setPath(request.getContextPath());
		//向客户端发送Cookie	
		response.addCookie(cookieUsername);
		response.addCookie(cookiePassword);
		
		map.put("info", "登录成功！正在跳往组织主页！");
		return map;
	}

	@RequestMapping("/selectByLikeAname")
	@ResponseBody
	public List selectByLikeAname(String alike, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Organization org = (Organization) session.getAttribute("org");
		return orgService.selectByLikeAname(org.getOid(), alike);
		
	}
	
	
	@RequestMapping("/createGroup")
	@ResponseBody
	public Map createGroup(String groupName, HttpServletRequest request) {
		Map map = new HashMap<>();
		try {
			MyGroup g = new MyGroup();
			g.setGname(groupName);
			Organization org = (Organization) request.getSession().getAttribute("org");
			g.setOid(org.getOid());
			g.setGcreatetime(new Date());
			groupService.createMyGroup(g);
			map.put("info", "创建成功！");
		}catch (Exception e) {
			e.printStackTrace();
			map.put("info", "创建失败！");
		}
		return map;
	}
	
	@RequestMapping("/createActivity")
	@ResponseBody
	public Map createActivity(@RequestBody Activity activity, HttpServletRequest request, HttpServletResponse response) {
		Map map = new HashMap<>();
		System.out.println(activity.getAstartTime());
		System.out.println(activity.getAendTime());
		try {
			HttpSession session = request.getSession();
			Organization org = (Organization) session.getAttribute("org");
			activity.setOid(org.getOid());
			System.out.println(activity);
			activity.setAstatus("1");
			activityService.createActivity(activity);
			map.put("info", "创建成功！");
		}catch (Exception e) {
			map.put("info", "创建失败！");
		}
		return map;
	}
	
	@RequestMapping("/activityPage")
	@ResponseBody
	public PageResult activityPage(int page, int rows, HttpServletRequest request){
		Organization org = (Organization) request.getSession().getAttribute("org");
		return orgService.getOrgActivityDetail(page, rows, org.getOid());
	}
	
	@RequestMapping("/getGroupList")
	@ResponseBody
	public List getGroupList(Integer oid, HttpServletRequest request){
		Organization org = (Organization) request.getSession().getAttribute("org");
		return groupService.getGroupList(org.getOid());
	}
	
	@RequestMapping("/getGroupDetail")
	@ResponseBody
	public PageResult getGroupDetail(int page, int rows, HttpServletRequest request){
		Organization org = (Organization) request.getSession().getAttribute("org");
		return orgService.getGroupDetail(page, rows, org.getOid());
	}
	@RequestMapping("/modifyActivity")
	@ResponseBody
	public Map modifyActivity(@RequestBody Activity activity, HttpServletRequest request) {
		Map map = new HashMap<>();
		System.out.println(activity.getAstartTime());
		System.out.println(activity.getAendTime());
		try {
			HttpSession session = request.getSession();
			Organization org = (Organization) session.getAttribute("org");
			activity.setOid(org.getOid());
			System.out.println(activity);
			activity.setAstatus("1");
			activityService.updateByPrimaryKey(activity);
			map.put("info", "修改成功！");
		}catch (Exception e) {
			map.put("info", "修改失败！");
		}
		return map;
	}
	
	@RequestMapping("/deleteActivity")
	@ResponseBody
	public Map deleteActivity(Integer aid, HttpServletRequest request) {
		Map map = new HashMap<>();
		try {
			activityService.deleteByPrimaryKey(aid);
			map.put("info", "删除成功！");
		}catch (Exception e) {
			map.put("info", "删除失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/deleteGroup")
	@ResponseBody
	public Map deleteGroup(Integer gid, HttpServletRequest request) {
		Map map = new HashMap<>();
		try {
			groupService.deleteByPrimaryKey(gid);
			map.put("info", "删除成功！");
		}catch (Exception e) {
			map.put("info", "删除失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getGidUsedNum")
	@ResponseBody
	public Map getGidUsedNum(Integer gid, HttpServletRequest request) {
		Map map = new HashMap<>();
		try {
			Integer gidUsedNum = groupService.getGidUsedNum(gid);
			map.put("info", gidUsedNum);
		}catch (Exception e) {
			map.put("info", "查询失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/addOrgAdmin")
	@ResponseBody
	public Map addOrgAdmin(String addOrgAdmin, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Organization org = (Organization) session.getAttribute("org");
		
		Map map = new HashMap<>();
		try {
			if(orgService.addAdmin(org.getOid(), addOrgAdmin))			
				map.put("info", "添加管理员成功！");
			else
				map.put("info", "此学号已是管理员！");
		}catch (Exception e) {
			map.put("info", "添加管理员失败");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/selectOrgAdmins")
	@ResponseBody
	public Map selectOrgAdmins(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Organization org = (Organization) session.getAttribute("org");
		Map map = new HashMap<>();
		try {
			List<OrgAdminsPojo> list = orgService.selectOrgAdmins(org.getOid());
			map.put("info", list);
		}catch (Exception e) {
			map.put("info", "添加管理员失败");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/deleteOrgAdmins")
	@ResponseBody
	public Map deleteOrgAdmins(String uid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Organization org = (Organization) session.getAttribute("org");
		Map map = new HashMap<>();
		try {
			orgService.deleteOrgAdmins(org.getOid(), uid);
			map.put("info", "删除成功！");
		}catch (Exception e) {
			map.put("info", "删除失败！");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getCurrentEmail")
	@ResponseBody
	public Map getCurrentEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Organization org = (Organization) session.getAttribute("org");
		Map map = new HashMap<>();
		try {
			String email = orgService.getEmail(org.getOid());
			map.put("info", email);
		}catch (Exception e) {
			map.put("info", "error");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/realChangePwd")
	@ResponseBody
	public Map realChangePwd(String newPwd, String vercode, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Organization org = (Organization) session.getAttribute("org");
		Map map = new HashMap<>();
		
		String sessionVercode = (String)session.getAttribute("newpwdVerCode"+org.getOemail());
		System.out.println(sessionVercode);
		System.out.println(vercode);
		System.out.println(newPwd);
		if(sessionVercode == null || !sessionVercode.equals(vercode)) {
			map.put("info", "验证码错误！");
			return map;
		}
		
		try {
			orgService.changePwd(org.getOid(), newPwd);
			//创建Cookie
			Cookie cookiePassword = new Cookie("cookieOpwd", newPwd);
			//持久化时间为31天
			cookiePassword.setMaxAge(60*60*24*31);
			//设置cookie的携带路径，访问WEB应用所有的资源都携带此Cookie
			cookiePassword.setPath(request.getContextPath());
			//向客户端发送Cookie	
			response.addCookie(cookiePassword);
			
			Organization oorg = (Organization)session.getAttribute("org");
			
			oorg.setOpwd(newPwd);
			
			session.setAttribute("org", oorg);
			
			System.out.println(oorg);
			
			map.put("info", "修改成功！");
		}catch (Exception e) {
			map.put("info", "修改失败");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/realChangeEmail")
	@ResponseBody
	public Map realChangeEmail(String oldvercode, String newvercode, String newEmail, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Organization org = (Organization) session.getAttribute("org");
		Map map = new HashMap<>();
		
		String newEmailVercode = (String)session.getAttribute("newEmailVercode"+newEmail);
		String oldEmailVercode = (String)session.getAttribute("oldEmailVercode"+org.getOemail());
		if(newEmailVercode == null || !newEmailVercode.equals(newvercode) || oldEmailVercode == null || !oldEmailVercode.equals(oldvercode)) {
			map.put("info", "验证码错误！");
			return map;
		}
		
		try {
			orgService.changeEmail(org.getOid(), newEmail);
			Cookie cookieUsername = new Cookie("cookieOemail", org.getOemail());
			cookieUsername.setMaxAge(60*60*24*31);
			cookieUsername.setPath(request.getContextPath());
			response.addCookie(cookieUsername);
			
			Organization oorg = (Organization)session.getAttribute("org");
			
			oorg.setOemail(newEmail);
			
			session.setAttribute("org", oorg);
			
			System.out.println(oorg);
			
			map.put("info", "修改成功！");
		}catch (Exception e) {
			map.put("info", "修改失败");
			e.printStackTrace();
		}
		return map;
	}
	
	
	
}
