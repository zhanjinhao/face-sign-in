package cn.facesignin.mapper;

import java.util.List;

import cn.facesignin.pojo.controller.AdminAct;
import cn.facesignin.pojo.controller.AdminOrg;

public interface AdminMapper {
	
	List<AdminOrg> getAdminOrgDetails();
	
	List<AdminAct> getAdminUsers(String like);
}