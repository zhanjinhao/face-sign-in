package cn.facesignin.service;

import cn.facesignin.entity.PageResult;

public interface AdminService {
	PageResult getAdminOrgDetails(int page, int rows);

	PageResult selectOrgs(String like);

	PageResult getAdminActDetails(int page, int rows, String like);
	
	PageResult getAdminUsers(int page, int rows, String like);
}
