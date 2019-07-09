package cn.facesignin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.facesignin.entity.PageResult;
import cn.facesignin.mapper.SigninRecordMapper;
import cn.facesignin.pojo.SigninRecord;
import cn.facesignin.pojo.SigninRecordExample;
import cn.facesignin.pojo.SigninRecordExample.Criteria;
import cn.facesignin.service.SignInRecordService;

@Service
public class SignInRecordServiceImpl implements SignInRecordService {

	@Autowired
	private SigninRecordMapper signinRecordMapper;
	
	@Override
	public void insert(SigninRecord signinRecord) {
		signinRecordMapper.insert(signinRecord);
	}

	@Override
	public SigninRecord getRecordByAidAndUid(String uid, Integer aid) {
		SigninRecordExample example = new SigninRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andAidEqualTo(aid);
		criteria.andUidEqualTo(uid);
		List<SigninRecord> selectByExample = signinRecordMapper.selectByExample(example);
		return (selectByExample==null || selectByExample.size() == 0)?null:selectByExample.get(0);
	}

	@Override
	public PageResult selectSigninRecordByAid(int pageNum, int pageSize, Integer aid) {
		SigninRecordExample example = new SigninRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andAidEqualTo(aid);
		PageHelper.startPage(pageNum, pageSize);
		Page<SigninRecord> page = (Page<SigninRecord>)signinRecordMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public PageResult selectByLikeId(Integer aid, String ulike) {
		SigninRecordExample example = new SigninRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidLike("%"+ulike+"%");
		criteria.andAidEqualTo(aid);
		List<SigninRecord> list = signinRecordMapper.selectByExample(example);
		return new PageResult(list.size(), list);
	}

	@Override
	public List<SigninRecord> selectAllSigninRecordByAid(Integer aid) {
		SigninRecordExample example = new SigninRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andAidEqualTo(aid);
		return signinRecordMapper.selectByExample(example);
	}
}
