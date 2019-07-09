package cn.facesignin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.facesignin.constant.ServerConfig;
import cn.facesignin.constant.ThreadPoolConfig;
import cn.facesignin.entity.PageResult;
import cn.facesignin.face.FaceUtils;
import cn.facesignin.mq.Entity;
import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.OrgadminsKey;
import cn.facesignin.pojo.Organization;
import cn.facesignin.pojo.SigninRecord;
import cn.facesignin.pojo.User;
import cn.facesignin.pojo.UserJoinGroup;
import cn.facesignin.service.ActivityService;
import cn.facesignin.service.MyGroupService;
import cn.facesignin.service.OrgService;
import cn.facesignin.service.OrgadminsService;
import cn.facesignin.service.SignInRecordService;
import cn.facesignin.service.UserJoinGroupService;
import cn.facesignin.service.UserService;
import cn.facesignin.service.VerifyService;
import cn.facesignin.thread.Verify;
import cn.facesignin.utils.QrCodeUtil;
import cn.facesignin.utils.ZipUtils;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	private ExecutorService verifyThreadPool = Executors.newFixedThreadPool(ThreadPoolConfig.VERIFY);
	
	ZipUtils zipUtils = new ZipUtils();
	
	FaceUtils faceUtils = new FaceUtils();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SignInRecordService signInRecordService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private MyGroupService groupService;
	
	@Autowired
	private VerifyService verifyService;
	
	@Autowired
	private UserJoinGroupService userJoinGroupService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private OrgadminsService orgadminsService;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@RequestMapping("/getActivityName")
	@ResponseBody
	public Map getActivityName(HttpServletRequest request) {
		Map map = new HashMap<>();
		HttpSession session = request.getSession();
		Integer aid = (Integer) session.getAttribute("aid");
		Activity activity = activityService.getActivityByAid(aid);
		map.put("activityName", activity.getAname());
		return map;
	}
	
	@RequestMapping("/inputInfo")
	@ResponseBody
	public Map inputInfo(String uid, HttpServletRequest request) {
		Map map = new HashMap<>();
		HttpSession session = request.getSession();
		Integer aid = (Integer) session.getAttribute("aid");
		
		Activity activity = activityService.getActivityByAid(aid);
		User user = userService.selectUserByUid(uid);
		if(user == null) {
			map.put("info", "瀛﹀彿涓嶅瓨鍦紒");
			return map;
		}
		if(activity.getGid() != 10000) {
			
			UserJoinGroup userJoinGroup = userJoinGroupService.getRecordByGidAndUid(uid, activity.getGid());
			
			if(userJoinGroup == null) {
				map.put("info", "瀛﹀彿涓嶅湪缁勪腑锛�");
				return map;
			}
			
		}
		
		SigninRecord record = signInRecordService.getRecordByAidAndUid(uid, (Integer)session.getAttribute("aid"));
		
		if(record != null) {
			map.put("info", "姝よ处鎴峰凡绛惧埌锛�");
			return map;
		}
		try {
			SigninRecord signinRecord = new SigninRecord(uid, (Integer)session.getAttribute("aid"));
			activityService.inputInfo(signinRecord);
			map.put("info", "褰曞叆鎴愬姛锛�");
		}catch (Exception e) {
			e.printStackTrace();
			map.put("info", "褰曞叆澶辫触");
		}
		return map;
	}
	
	@RequestMapping("/deleteUserRecord")
	@ResponseBody
	public Map deleteUserRecord(String uid, HttpServletRequest request) {
		Map map = new HashMap<>();
		try {
			HttpSession session = request.getSession();
			Integer aid = (Integer)session.getAttribute("aid");
			activityService.deleteSigninRecord(uid, aid);
			map.put("info", "鍒犻櫎鎴愬姛锛�");
		}catch (Exception e) {
			e.printStackTrace();
			map.put("info", "鍒犻櫎澶辫触锛�");
		}
		return map;
	}
	
	@RequestMapping("/resigninRecord")
	@ResponseBody
	public Map resigninRecord(String uid, HttpServletRequest request) {
		Map map = new HashMap<>();
		try {
			HttpSession session = request.getSession();
			Integer aid = (Integer)session.getAttribute("aid");
			activityService.resigninRecord(uid, aid);
			map.put("info", "鏇存柊鎴愬姛锛�");
		}catch (Exception e) {
			e.printStackTrace();
			map.put("info", "鏇存柊澶辫触锛�");
		}
		return map;
	}
	
	@RequestMapping("/selectSigninRecordByAid")
	@ResponseBody
	public PageResult selectSigninRecordByAid(int page, int rows, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer aid = (Integer)session.getAttribute("aid");
		return signInRecordService.selectSigninRecordByAid(page, rows, aid);
	}
	
	@RequestMapping("/selectByLikeId")
	@ResponseBody
	public PageResult selectByLikeId(String ulike, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer aid = (Integer)session.getAttribute("aid");
		return signInRecordService.selectByLikeId(aid, ulike);
	}
	
	@RequestMapping("/activitySession")
	@ResponseBody
	public Map activitySession(Integer aid, HttpServletRequest request){
		Map map = new HashMap<>();
		try {
			HttpSession session = request.getSession();
			session.setAttribute("aid", aid);
			System.out.println("aid  ==>   " + aid);
			map.put("info", "1");
		}catch (Exception e) {
			map.put("info", "2");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/initMobileVerify")
	public void initMobileVerify(@RequestParam("aid")Integer aid, @RequestParam("email")String email, @RequestParam("time")long time,
			HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		if(new Date().getTime() - time > 1800000) {
			session.setAttribute("initMobileVerifyInfo", "浜岀淮鐮佽繃鏈�");
			response.sendRedirect(request.getContextPath() + "/ui/error.action");
			return;
		}
		
		Organization organization = orgService.selectOrgByEmail(email);
		Map map = new HashMap<>();
		
		Organization oldOrg = (Organization)session.getAttribute("org");
		if(oldOrg != null && oldOrg.getOemail().equals(email)) {
			session.setAttribute("aid", aid);
			response.sendRedirect(request.getContextPath() + "/ui/mobileVerify.action");
			return;
		}
			
		try {
			//璁剧疆org
			session.setAttribute("org", organization);
			//璁剧疆aid
			session.setAttribute("aid", aid);
		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("initMobileVerifyInfo", "瀹曟満涓紒");
			response.sendRedirect(request.getContextPath() + "/ui/error.action");
			return;
		}

		User user = (User)session.getAttribute("user");
		
		OrgadminsKey orgadmin = orgadminsService.getOrgadmin(user.getUid(), organization.getOid());
		if(orgadmin == null) {
			session.setAttribute("initMobileVerifyInfo", "鎮ㄨ繕涓嶆槸绠＄悊鍛橈紒");
			response.sendRedirect(request.getContextPath() + "/ui/error.action");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/ui/mobileVerify.action");
	}
	
	@RequestMapping("/initVerify")
	@ResponseBody
	public Map initVerify(HttpServletRequest request){
		Map map = new HashMap<>();
		try {
			HttpSession session = request.getSession();
			Integer aid = (Integer)session.getAttribute("aid");
			map.put("aid", aid);
			
			//淇濆瓨缁勫唴鎴愬憳......
			Activity activity = activityService.getActivityByAid(aid);
			Integer gid = activity.getGid();
			if(gid == 10000) {
				//涓嶄娇鐢ㄧ粍
				List<User> userList = userService.getAllUsers();
				session.setAttribute("userList", userList);
			}else {
				//浣跨敤缁�
				List<User> userList = groupService.getUsersInGroup(activity.getGid());
				session.setAttribute("userList", userList);
				System.out.println(userList);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/upload")
	public void verity(@RequestParam("file") MultipartFile file, 
			@RequestParam("aid")Integer aid, HttpServletRequest request) {
		
		File tempSavedfile = verifyService.tempSaveFile(file, aid);
		HttpSession session = request.getSession();
		
		Entity entity = new Entity();
		entity.setAid(aid);
		entity.setFilePath(tempSavedfile.getAbsolutePath());
		entity.setUsers((List<User>) session.getAttribute("userList"));
		
		String str = JSON.toJSONString(entity);
		
		amqpTemplate.convertAndSend("mq.exChange", "mq.upload.send", str);
		
	}
	
	
	
	@RequestMapping("echoSigninUsers")
	@ResponseBody
	public Map echoSigninUsers(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ConcurrentLinkedQueue<String> users = (ConcurrentLinkedQueue<String>) session.getAttribute("signinUsers");
		Map map = new HashMap<>();
		
		if(users == null)
			return map;
		
		System.out.println(users);
		
		int size = users.size();
		List<String> usersName = new ArrayList<>();
		Object[] array = (Object [])users.toArray();
		int t = size - 14;
		if(t >= 0) {
			for(int i = t; i < size; i++) {
				usersName.add(array[i].toString());
			}
		}
		map.put("info", users);
		return map;
		
	}
	
	@RequestMapping("echoErrorJSPInfo")
	@ResponseBody
	public Map echoErrorJSPInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashMap<>();
		
		HttpSession session = request.getSession();
		
		String attribute = (String)session.getAttribute("initMobileVerifyInfo");
		
		map.put("info", "aaaaaaaaaa");
		
		return map;
	}
	
	@RequestMapping("testPath")
	public void testPath(HttpServletRequest request) {
		
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		
		System.out.println(basePath);
		
		System.out.println(request.getRequestURL());
		System.out.println(request.getScheme());
		System.out.println(request.getServletPath());
		System.out.println(request.getServerPort());
		System.out.println(request.getServerName());
		System.out.println(request.getRequestURI());
		
		System.out.println(request.getLocalAddr());
		System.out.println(request.getLocalName());
		System.out.println(request.getLocalPort());
		System.out.println(request.getPathInfo());
		System.out.println(request.getProtocol());
		
	}
	
	@RequestMapping("/qrcodes")
	@ResponseBody
	public void qrcodes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Integer aid = (Integer) session.getAttribute("aid");
		Organization org = (Organization) session.getAttribute("org");
		InetAddress serverHost = null;
		try {
			serverHost = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = ServerConfig.PROTOCOL + "://" + ServerConfig.SERVER_IP  + ServerConfig.PORT + "/activity/initMobileVerify.action?"
				+ "aid=" + aid + "&email=" + org.getOemail() + "&time=" + new Date().getTime();
		
		session.setAttribute("url", url);
		
		System.out.println(url);
		try {
			QrCodeUtil.createQrCode(url, response.getOutputStream(), 400, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	
    	HttpSession session = request.getSession();
		
		Integer aid = (Integer) session.getAttribute("aid");
		
		String zipFile = activityService.createZipFile(aid);
    	
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	
		try(
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFile));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				
			) {
			
			long fileLength = new File(zipFile).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String((aid + ".zip").getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
    
	@RequestMapping("deleteTempImg")
	public void deleteTempImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashMap<>();
		HttpSession session = request.getSession();
		Integer aid = (Integer)session.getAttribute("aid");
		activityService.deleteTempImg(aid);
	}
    
}
