package cn.facesignin.service;

import java.util.List;


import cn.facesignin.entity.PageResult;
import cn.facesignin.pojo.Organization;
import cn.facesignin.pojo.controller.OrgActivity;
import cn.facesignin.pojo.controller.OrgAdminsPojo;

public interface OrgService {
	Organization selectOrgByEmail(String email);
	
	Integer insertOrg(Organization	org);
	
	PageResult getGroupDetail(int pageNum, int pageSize, int oid);
	
	PageResult getOrgActivityDetail(int pageNum, int pageSize, int oid);
	
	List<OrgActivity> selectByLikeAname(Integer oid, String aname);

	Boolean addAdmin(Integer oid, String addOrgAdmin);

	List<OrgAdminsPojo> selectOrgAdmins(Integer oid);

	void deleteOrgAdmins(Integer oid, String uid);

	String getEmail(Integer oid);

	void changePwd(Integer oid, String newPwd);

	void changeEmail(Integer oid, String newEmail);
}
