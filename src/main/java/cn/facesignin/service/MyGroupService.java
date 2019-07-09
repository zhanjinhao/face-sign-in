package cn.facesignin.service;

import java.util.List;

import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.User;

public interface MyGroupService {
	public void createMyGroup(MyGroup myGroup);
	public List<MyGroup> getGroupList(Integer oid);
	public int deleteByPrimaryKey(Integer gid);
	public Integer getGidUsedNum(Integer gid);
	
	public List<User> getUsersInGroup(Integer gid);
}
