package cn.facesignin.service;

import cn.facesignin.entity.PageResult;
import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.SigninRecord;

public interface ActivityService {
	
	int resigninRecord(String uid, Integer aid);
	
	public void createActivity(Activity activity);
	
	public PageResult findPage(int pageNum, int pageSize, int oid);
	
	int updateByPrimaryKey(Activity record);
	
	int deleteByPrimaryKey(Integer aid);
	
	int inputInfo(SigninRecord record);
	
	int deleteSigninRecord(String uid, Integer aid);
	
	Activity getActivityByAid(Integer aid);
	
	String createZipFile(Integer aid) throws Exception;
	
	void deleteTempImg(Integer aid) throws Exception;
	
}