package cn.facesignin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.facesignin.mapper.OrgadminsMapper;
import cn.facesignin.pojo.OrgadminsExample;
import cn.facesignin.pojo.OrgadminsExample.Criteria;
import cn.facesignin.pojo.OrgadminsKey;
import cn.facesignin.service.OrgadminsService;

@Service
public class OrgadminsServiceImpl implements OrgadminsService{

	@Autowired
	private OrgadminsMapper orgadminsMapper;
	
	@Override
	public OrgadminsKey getOrgadmin(String uid, Integer oid) {
		
		OrgadminsExample example = new OrgadminsExample();
		Criteria criteria = example.createCriteria();
		criteria.andOidEqualTo(oid);
		criteria.andUidEqualTo(uid);
		
		List<OrgadminsKey> list = orgadminsMapper.selectByExample(example);
		
		return list != null? list.get(0) : null;
	}
	
}
