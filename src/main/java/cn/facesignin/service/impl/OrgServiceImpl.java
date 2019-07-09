package cn.facesignin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.facesignin.entity.PageResult;
import cn.facesignin.mapper.ActivityMapper;
import cn.facesignin.mapper.OrgadminsMapper;
import cn.facesignin.mapper.OrganizationMapper;
import cn.facesignin.pojo.OrgadminsExample;
import cn.facesignin.pojo.OrgadminsKey;
import cn.facesignin.pojo.Organization;
import cn.facesignin.pojo.OrganizationExample;
import cn.facesignin.pojo.OrganizationExample.Criteria;
import cn.facesignin.pojo.controller.GroupDetail;
import cn.facesignin.pojo.controller.OrgActivity;
import cn.facesignin.pojo.controller.OrgAdminsPojo;
import cn.facesignin.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private OrgadminsMapper orgadminsMapper;
	
	@Override
	public Organization selectOrgByEmail(String email) {
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andOemailEqualTo(email);
		List<Organization> list = organizationMapper.selectByExample(example);
		return (list==null || list.size() == 0)?null:list.get(0);
	}

	@Override
	public Integer insertOrg(Organization org) {
		organizationMapper.insertAndReturnPrimaryKey(org);
		return org.getOid();
	}

	@Override
	public PageResult getGroupDetail(int pageNum, int pageSize, int oid) {
		PageHelper.startPage(pageNum, pageSize);
		Page<GroupDetail> page = (Page<GroupDetail>)organizationMapper.getGroupDetail(oid);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public PageResult getOrgActivityDetail(int pageNum, int pageSize, int oid) {
		PageHelper.startPage(pageNum, pageSize);
		Page<OrgActivity> page = (Page<OrgActivity>)organizationMapper.getOrgActivityDetail(oid);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<OrgActivity> selectByLikeAname(Integer oid, String aname) {
		return organizationMapper.selectByLikeAname(oid, aname);
	}

	@Override
	public Boolean addAdmin(Integer oid, String addOrgAdmin) {
		
		OrgadminsExample example = new OrgadminsExample();
		cn.facesignin.pojo.OrgadminsExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andOidEqualTo(oid);
		createCriteria.andUidEqualTo(addOrgAdmin);
		
		List<OrgadminsKey> list = orgadminsMapper.selectByExample(example);
		
		if(list != null && list.size() > 0)
			return false;
		OrgadminsKey record = new OrgadminsKey();
		record.setOid(oid);
		record.setUid(addOrgAdmin);
		
		orgadminsMapper.insert(record);
		return true;
	}

	@Override
	public List<OrgAdminsPojo> selectOrgAdmins(Integer oid) {
		return orgadminsMapper.selectOrgAdmins(oid);
	}

	@Override
	public void deleteOrgAdmins(Integer oid, String uid) {
		
		OrgadminsExample example = new OrgadminsExample();
		cn.facesignin.pojo.OrgadminsExample.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		criteria.andOidEqualTo(oid);
		
		orgadminsMapper.deleteByExample(example);
		
	}

	@Override
	public String getEmail(Integer oid) {
		return organizationMapper.selectByPrimaryKey(oid).getOemail();
	}

	@Override
	public void changePwd(Integer oid, String newPwd) {
		
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andOidEqualTo(oid);
		
		Organization organization = organizationMapper.selectByPrimaryKey(oid);
		organization.setOpwd(newPwd);
		
		organizationMapper.updateByExample(organization, example);
		
	}

	@Override
	public void changeEmail(Integer oid, String newEmail) {
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andOidEqualTo(oid);
		
		Organization organization = organizationMapper.selectByPrimaryKey(oid);
		organization.setOemail(newEmail);
		
		organizationMapper.updateByExample(organization, example);
		
	}

	

}
