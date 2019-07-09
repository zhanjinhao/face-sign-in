package cn.facesignin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.facesignin.entity.PageResult;
import cn.facesignin.mapper.UserMapper;
import cn.facesignin.pojo.User;
import cn.facesignin.pojo.UserExample;
import cn.facesignin.pojo.UserExample.Criteria;
import cn.facesignin.pojo.controller.UserActivity;
import cn.facesignin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User selectUserByEmail(String email) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUemailEqualTo(email);
		List<User> selectByExample = userMapper.selectByExample(userExample);
		return (selectByExample==null || selectByExample.size() == 0)?null:selectByExample.get(0);
	}

	@Override
	public User selectUserByUid(String uid) {
		User user = userMapper.selectByPrimaryKey(uid);
		return user;
	}

	@Override
	public void insertUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public User selectUserByFaceToken(String faceToken) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUfaceTokenEqualTo(faceToken);
		List<User> selectByExample = userMapper.selectByExample(example);
		return (selectByExample==null || selectByExample.size() == 0)?null:selectByExample.get(0);
	}

	@Override
	public List<User> getAllUsers() {
		return userMapper.selectByExample(null);
	}

	@Override
	public PageResult getUserActivityNotInGroup(int page, int rows, String uid) {
		
		PageHelper.startPage(page, rows);
		Page<UserActivity> mpage = (Page<UserActivity>)userMapper.getUserActivityNotInGroup(uid);
		return new PageResult(mpage.getTotal(), mpage.getResult());
	
	}

	@Override
	public PageResult getUserActivityInGroup(int page, int rows, String uid, Integer gid) {

		PageHelper.startPage(page, rows);
		Page<UserActivity> mpage = (Page<UserActivity>)userMapper.getUserActivityInGroup(uid, gid);
		return new PageResult(mpage.getTotal(), mpage.getResult());
		
	}

	@Override
	public String getEmail(String uid) {
		
		User user = userMapper.selectByPrimaryKey(uid);
		
		return user.getUemail();
	}

	@Override
	public void changePwd(String uid, String newPwd) {
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		
		User key = userMapper.selectByPrimaryKey(uid);
		key.setUpwd(newPwd);
		
		userMapper.updateByExample(key, example);
		
	}

	@Override
	public void changeEmail(String uid, String newEmail) {
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andUidEqualTo(uid);
		
		User user = userMapper.selectByPrimaryKey(uid);
		user.setUemail(newEmail);
		
		userMapper.updateByExample(user, example);
		
	}

	@Override
	public void updateFaceToken(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andUidEqualTo(user.getUid());
		
		userMapper.updateByExample(user, example);
	}
	
}
