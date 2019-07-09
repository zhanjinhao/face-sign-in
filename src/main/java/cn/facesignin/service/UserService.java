package cn.facesignin.service;

import java.util.List;

import cn.facesignin.entity.PageResult;
import cn.facesignin.pojo.User;

public interface UserService {
	User selectUserByEmail(String email);

	User selectUserByUid(String uid);
	
	void insertUser(User user);
	
	User selectUserByFaceToken(String faceToken);
	
	List<User> getAllUsers();
	
    PageResult getUserActivityNotInGroup(int page, int rows, String uid);
    
    PageResult getUserActivityInGroup(int page, int rows, String uid, Integer gid);

	String getEmail(String uid);

	void changePwd(String uid, String newPwd);

	void changeEmail(String uid, String newEmail);

	void updateFaceToken(User user);
	
}
