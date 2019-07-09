package cn.facesignin.controller;

import java.io.File;
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

import cn.facesignin.constant.FaceppConfig;
import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.entity.PageResult;
import cn.facesignin.face.FaceUtils;
import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.User;
import cn.facesignin.service.UserJoinGroupService;
import cn.facesignin.service.UserService;
import cn.facesignin.utils.FileUtils;
import cn.facesignin.utils.ImgFileBase64Utils;

@Controller
@RequestMapping("/user")
public class UserController {

	private FaceUtils faceutils = new FaceUtils();
	
	@Autowired
	private UserService	userService;
	
	@Autowired
	private UserJoinGroupService userJoinGroup;
	
	@ResponseBody
	@RequestMapping("/register")
	public Map register(@RequestBody User user, HttpServletRequest request) {
		//user的ufaceToken传输照片的base64编码
		//user的uimgPath传输邮箱验证码
		Map map = new HashMap<>();

		HttpSession session = request.getSession();
		String uemailVerCode = (String)session.getAttribute("uemailVerCode" + user.getUemail());
		String uid = user.getUid();

		//判断验证码
		if(uemailVerCode == null || !uemailVerCode.equals(user.getUimgPath())) {
			map.put("info", "验证码错误！");
			return map;
		}
		
		String base64 = user.getUfaceToken();
		//照片只能有一张人脸
		if(!(faceutils.getFaceNumWithFacepp(base64) == 1)) {
			map.put("info", "照片人脸数量须为 1 ！");
			return map;
		}
		
		user.setUfaceToken(faceutils.getFaceTokenByBase64(base64));
		userService.insertUser(user);
		faceutils.addFace(FaceppConfig.FACESET_NAME, user.getUfaceToken());
		
		//存储用户照片。保存路径：ROOT/年级专业/学号.jpg
		String gradeAndMajor = FileUtils.getGradeAndMajorFromUid(uid);
		boolean b = ImgFileBase64Utils.decodeBase64ToImgFile(base64, 
				ImgFilePathConfig.ROOT + File.separator+ gradeAndMajor + File.separator + uid + ".jpg");
		user.setUimgPath(uid + ".jpg");
		if(!b) {
			map.put("info", "照片存在问题，请缩小文件大小后重新上传！");
			return map;
		}
		
		map.put("info", "注册成功，正在跳往登录界面！");
		session.removeAttribute("uemailVerCode" + user.getUemail());
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/changUserImg")
	public Map changUserImg(@RequestBody User user, HttpServletRequest request) {
		//user的ufaceToken传输照片的base64编码
		//user的uimgPath传输邮箱验证码
		Map map = new HashMap<>();
		
		HttpSession session = request.getSession();
		User uuser = (User)session.getAttribute("user");
		
		String uemailVerCode = (String)session.getAttribute("uemailVerCode" + uuser.getUemail());
		String uid = uuser.getUid();
		
		//判断验证码
		if(uemailVerCode == null || !uemailVerCode.equals(user.getUimgPath())) {
			map.put("info", "验证码错误！");
			return map;
		}
		
		String base64 = user.getUfaceToken();
		//照片只能有一张人脸
		if(!(faceutils.getFaceNumWithFacepp(base64) == 1)) {
			map.put("info", "照片人脸数量须为 1 ！");
			return map;
		}
		
		System.out.println(faceutils.removeFace(FaceppConfig.FACESET_NAME, uuser.getUfaceToken()));
		
		uuser.setUfaceToken(faceutils.getFaceTokenByBase64(base64));
		userService.updateFaceToken(uuser);
		
		faceutils.addFace(FaceppConfig.FACESET_NAME, uuser.getUfaceToken());
		
		//存储用户照片。保存路径：ROOT/年级专业/学号.jpg
		String gradeAndMajor = FileUtils.getGradeAndMajorFromUid(uid);
		boolean b = ImgFileBase64Utils.decodeBase64ToImgFile(base64, 
				ImgFilePathConfig.ROOT + File.separator+ gradeAndMajor + File.separator + uid + ".jpg");
		user.setUimgPath(uid + ".jpg");
		if(!b) {
			map.put("info", "照片存在问题，请缩小文件大小后重新上传！");
			return map;
		}
		
		map.put("info", "更换成功！");
		session.removeAttribute("uemailVerCode" + user.getUemail());
		return map;
	}
	
	

	@RequestMapping("/getCurrentEmail")
	@ResponseBody
	public Map getCurrentEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map map = new HashMap<>();
		try {
			String email = userService.getEmail(user.getUid());
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
		User user = (User) session.getAttribute("user");
		Map map = new HashMap<>();
		
		String sessionVercode = (String)session.getAttribute("newpwdVerCode"+user.getUemail());
		System.out.println(sessionVercode);
		System.out.println(vercode);
		System.out.println(newPwd);
		if(sessionVercode == null || !sessionVercode.equals(vercode)) {
			map.put("info", "验证码错误！");
			return map;
		}
		
		try {
			userService.changePwd(user.getUid(), newPwd);
			//创建Cookie
			Cookie cookiePassword = new Cookie("cookieUpwd", newPwd);
			//持久化时间为31天
			cookiePassword.setMaxAge(60*60*24*31);
			//设置cookie的携带路径，访问WEB应用所有的资源都携带此Cookie
			cookiePassword.setPath(request.getContextPath());
			//向客户端发送Cookie
			response.addCookie(cookiePassword);
			
			User uuser = (User)session.getAttribute("user");
			
			uuser.setUpwd(newPwd);
			
			session.setAttribute("user", uuser);
			
			System.out.println(uuser);
			
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
		User user = (User) session.getAttribute("user");
		Map map = new HashMap<>();
		
		String newEmailVercode = (String)session.getAttribute("newEmailVercode"+newEmail);
		String oldEmailVercode = (String)session.getAttribute("oldEmailVercode"+user.getUemail());
		if(newEmailVercode == null || !newEmailVercode.equals(newvercode) || oldEmailVercode == null || !oldEmailVercode.equals(oldvercode)) {
			map.put("info", "验证码错误！");
			return map;
		}
		
		try {
			userService.changeEmail(user.getUid(), newEmail);
			User uuser = (User)session.getAttribute("user");
			uuser.setUemail(newEmail);
			session.setAttribute("user", uuser);
			System.out.println(uuser);
			map.put("info", "修改成功！");
		}catch (Exception e) {
			map.put("info", "修改失败");
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getUserJoinGroupDetail")
	public PageResult getUserJoinGroupDetail(int page, int rows, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return userJoinGroup.getUserJoinGroupDetail(page, rows, user.getUid());
	}

	@ResponseBody
	@RequestMapping("/getUserActivityNotInGroup")
	public PageResult getUserActivityNotInGroup(int page, int rows, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return userService.getUserActivityNotInGroup(page, rows, user.getUid());
	}
	
	@ResponseBody
	@RequestMapping("/getUserActivityInGroup")
	public PageResult getUserActivityInGroup(int page, int rows, Integer gid, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return userService.getUserActivityInGroup(page, rows, user.getUid(), gid);
	}
	
	@RequestMapping("/getUserName")
	@ResponseBody
	public Map getUserName(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map map = new HashMap<>();
		System.out.println(user.getUname());
		map.put("userName", user.getUname());
		return map;
	}
	
	
	@RequestMapping("/exitGroup")
	@ResponseBody
	public void exitGroup(Integer gid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map map = new HashMap<>();
		userJoinGroup.exitGroup(gid, user.getUid());
	}
	
	@RequestMapping("/getUserActivity")
	@ResponseBody
	public void getUserActivity(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map map = new HashMap<>();
	}
	
	@RequestMapping("/getUserJoinGroupList")
	@ResponseBody
	public List<MyGroup> getUserJoinGroupList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return userJoinGroup.getUserJoinGroupList(user.getUid());
	}
	
	
	
	@RequestMapping("/checkUemail")
	@ResponseBody
	public Map checkUemail(String email) {
		Map map = new HashMap<>();
		User user = userService.selectUserByEmail(email);
		if(user == null) {
			map.put("info", true);
		}else
			map.put("info", false);
		return map;
	}
	@RequestMapping("/checkUid")
	@ResponseBody
	public Map checkUid(String uid) {
		Map map = new HashMap<>();
		User user = userService.selectUserByUid(uid);
		if(user == null) {
			map.put("info", true);
		}else
			map.put("info", false);
		return map;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Map login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		//user的uname传递verCode
		HttpSession session = request.getSession();
		String rightVerCode = (String) session.getAttribute("rightVerCode");
		Map map = new HashMap<>();
		if(rightVerCode == null || !rightVerCode.equals(user.getUname())) {
			map.put("info", "验证码错误！");
			return map;
		}
		
		User userFromDB = userService.selectUserByUid(user.getUid());
		if(userFromDB == null || !userFromDB.getUpwd().equals(user.getUpwd())) {
			map.put("info", "账户或密码错误！");
			return map;
		}
		
		//创建Cookie
		Cookie cookieUsername = new Cookie("cookieUid", userFromDB.getUid());
		Cookie cookiePassword = new Cookie("cookieUpwd", userFromDB.getUpwd());
		//持久化时间为31天
		cookiePassword.setMaxAge(60*60*24*31);
		cookieUsername.setMaxAge(60*60*24*31);
		//设置cookie的携带路径，访问WEB应用所有的资源都携带此Cookie
		cookieUsername.setPath(request.getContextPath());
		cookiePassword.setPath(request.getContextPath());
		//向客户端发送Cookie	
		response.addCookie(cookieUsername);
		response.addCookie(cookiePassword);
		
		session.setAttribute("user", userFromDB);
		
		map.put("info", "登录成功！正在跳往用户主页！");
		return map;
	}
	
	@RequestMapping("/joinGroup")
	@ResponseBody
	public Map joinGroup(Integer gid, HttpServletRequest request) {
		Map map = new HashMap<>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			System.out.println(gid + "  " + user.getUid());
			userJoinGroup.joinMyGroup(gid, user.getUid());
			map.put("info", "加入成功！");
		}catch (Exception e) {
			e.printStackTrace();
			map.put("info", "加入失败！");
		}
		return map;
	}
	
	/*@RequestMapping("/findPage")
	@ResponseBody
	public PageResult findPage(int page, int rows, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		return activityService.findPage(page, rows, org.getOid());
	}*/
	
}