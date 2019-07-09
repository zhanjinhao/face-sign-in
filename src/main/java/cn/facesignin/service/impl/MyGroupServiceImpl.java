package cn.facesignin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.facesignin.mapper.MyGroupMapper;
import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.User;
import cn.facesignin.service.MyGroupService;

@Service
public class MyGroupServiceImpl implements MyGroupService {

	@Autowired
	private MyGroupMapper myGroupMapper;
	
	@Override
	public void createMyGroup(MyGroup myGroup) {
		myGroupMapper.insert(myGroup);
	}

	@Override
	public List<MyGroup> getGroupList(Integer oid) {
		return myGroupMapper.getGroupList(oid);
	}

	@Override
	public int deleteByPrimaryKey(Integer gid) {
		return myGroupMapper.deleteByPrimaryKey(gid);
	}

	@Override
	public Integer getGidUsedNum(Integer gid) {
		return myGroupMapper.getGidUsedNum(gid);
	}

	@Override
	public List<User> getUsersInGroup(Integer gid) {
		return myGroupMapper.getUsersInGroup(gid);
	}

}
