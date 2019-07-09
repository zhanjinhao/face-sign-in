package cn.facesignin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.facesignin.entity.PageResult;
import cn.facesignin.service.AdminService;
import cn.facesignin.service.OrgService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping("/getAdminOrgDetails")
	@ResponseBody
	public PageResult getAdminOrgDetails(int page, int rows) {
		return adminService.getAdminOrgDetails(page, rows);
	}
	
	@RequestMapping("/selectOrgs")
	@ResponseBody
	public PageResult selectOrgs(String like) {
		return adminService.selectOrgs(like);
	}
	
	@RequestMapping("/getAdminActDetails")
	@ResponseBody
	public PageResult getAdminActDetails(int page, int rows, String like) {
		return adminService.getAdminActDetails(page, rows, like);
	}
	
	@RequestMapping("/getAdminUsers")
	@ResponseBody
	public PageResult getAdminUsers(int page, int rows, String like) {
		return adminService.getAdminUsers(page, rows, like);
	}
	
	@RequestMapping("/getOrgActivityDetail")
	@ResponseBody
	public PageResult getOrgActivityDetail(int page, int rows, int oid, HttpServletRequest request) {
		return orgService.getOrgActivityDetail(page, rows, oid);
	}
}
