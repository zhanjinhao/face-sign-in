package cn.facesignin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.facesignin.entity.PageResult;
import cn.facesignin.mapper.AdminMapper;
import cn.facesignin.mapper.OrganizationMapper;
import cn.facesignin.pojo.controller.AdminAct;
import cn.facesignin.pojo.controller.AdminOrg;
import cn.facesignin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Override
	public PageResult getAdminOrgDetails(int page, int rows) {
		
		PageHelper.startPage(page, rows);
		Page<AdminOrg> mpage = (Page<AdminOrg>)adminMapper.getAdminOrgDetails();
		
		return new PageResult(mpage.getTotal(), mpage.getResult());
	}

	@Override
	public PageResult selectOrgs(String like) {
		List<AdminOrg> list = organizationMapper.selectOrgByLikeName(like);
		
		return new PageResult(list.size(), list);
	}

	@Override
	public PageResult getAdminActDetails(int page, int rows, String like) {
		
		List<AdminAct> list = organizationMapper.getAdminActDetails(like);
		
		return new PageResult(list.size(), list);
	}

	@Override
	public PageResult getAdminUsers(int page, int rows, String like) {
		List<AdminAct> list = adminMapper.getAdminUsers(like);
		return new PageResult(list.size(), list);
	}

}
