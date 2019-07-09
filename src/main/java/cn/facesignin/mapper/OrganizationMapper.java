package cn.facesignin.mapper;

import cn.facesignin.entity.PageResult;
import cn.facesignin.pojo.Organization;
import cn.facesignin.pojo.OrganizationExample;
import cn.facesignin.pojo.controller.AdminAct;
import cn.facesignin.pojo.controller.AdminOrg;
import cn.facesignin.pojo.controller.GroupDetail;
import cn.facesignin.pojo.controller.OrgActivity;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganizationMapper {
    int countByExample(OrganizationExample example);

    int deleteByExample(OrganizationExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Organization record);

    int insertSelective(Organization record);

    List<Organization> selectByExample(OrganizationExample example);

    Organization selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationExample example);
    
    List<GroupDetail> getGroupDetail(Integer oid);
    
    List<OrgActivity> getOrgActivityDetail(Integer oid);
    
    List<OrgActivity> selectByLikeAname(@Param("oid")Integer oid, @Param("aname")String aname);
    
    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

	void insertAndReturnPrimaryKey(Organization org);

	List<AdminOrg> selectOrgByLikeName(String like);
	
	List<AdminAct> getAdminActDetails(String like);
}