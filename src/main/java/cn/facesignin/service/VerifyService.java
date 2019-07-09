package cn.facesignin.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.facesignin.pojo.User;

public interface VerifyService {
	File tempSaveFile(MultipartFile file, Integer aid);
	
	public List<User> verify(File file, List<User> userList);

	void userSignInDB(List<User> users, Integer aid);
	
	void saveFile(File file, List<User> users, Integer aid);
}
