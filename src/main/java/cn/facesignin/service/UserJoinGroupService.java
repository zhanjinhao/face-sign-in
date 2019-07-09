package cn.facesignin.service;

import java.util.List;

import cn.facesignin.entity.PageResult;
import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.UserJoinGroup;

public interface UserJoinGroupService {
	public void joinMyGroup(Integer gid, String uid);
	PageResult getUserJoinGroupDetail(int page, int rows, String uid);
	public void exitGroup(Integer gid, String uid);
	public List<MyGroup> getUserJoinGroupList(String uid);
	public UserJoinGroup getRecordByGidAndUid(String uid, Integer gid);
}
