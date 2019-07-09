package cn.facesignin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.facesignin.entity.PageResult;
import cn.facesignin.mapper.UserJoinGroupMapper;
import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.UserJoinGroup;
import cn.facesignin.pojo.UserJoinGroupExample;
import cn.facesignin.pojo.UserJoinGroupExample.Criteria;
import cn.facesignin.pojo.controller.UserJoinGroupDetail;
import cn.facesignin.service.UserJoinGroupService;

@Service
public class UserJoinGroupServiceImpl implements UserJoinGroupService {

	@Autowired
	private UserJoinGroupMapper userJoinGroupMapper;

	@Override
	public void joinMyGroup(Integer gid, String uid) {
		
		UserJoinGroup userJoinGroup = new UserJoinGroup();
		userJoinGroup.setGid(gid);
		userJoinGroup.setUid(uid);
		userJoinGroupMapper.insert(userJoinGroup);
		
	}

	@Override
	public PageResult getUserJoinGroupDetail(int page, int rows, String uid) {
		PageHelper.startPage(page, rows);
		Page<UserJoinGroupDetail> pages = (Page<UserJoinGroupDetail>)userJoinGroupMapper.getUserJoinGroupDetail(uid);
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	@Override
	public void exitGroup(Integer gid, String uid) {
		UserJoinGroupExample example = new UserJoinGroupExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andGidEqualTo(gid);
		createCriteria.andUidEqualTo(uid);
		userJoinGroupMapper.deleteByExample(example);
	}

	@Override
	public List<MyGroup> getUserJoinGroupList(String uid) {
		return userJoinGroupMapper.getUserJoinGroupList(uid);
	}

	@Override
	public UserJoinGroup getRecordByGidAndUid(String uid, Integer gid) {
		UserJoinGroupExample example = new UserJoinGroupExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andGidEqualTo(gid);
		createCriteria.andUidEqualTo(uid);
		List<UserJoinGroup> list = userJoinGroupMapper.selectByExample(example);
		return (list == null || list.size() == 0) ? null : list.get(0);
	}

}
