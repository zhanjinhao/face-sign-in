package cn.facesignin.service;

import java.util.List;

import cn.facesignin.entity.PageResult;
import cn.facesignin.pojo.SigninRecord;

public interface SignInRecordService {
	void insert(SigninRecord signinRecord);
	
	SigninRecord getRecordByAidAndUid(String uid, Integer aid);
	
	PageResult selectSigninRecordByAid(int pageNum, int pageSize, Integer aid);

	PageResult selectByLikeId(Integer aid, String ulike);
	
	List<SigninRecord> selectAllSigninRecordByAid(Integer aid);
}
