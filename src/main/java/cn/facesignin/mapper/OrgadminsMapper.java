package cn.facesignin.mapper;

import cn.facesignin.pojo.OrgadminsExample;
import cn.facesignin.pojo.OrgadminsKey;
import cn.facesignin.pojo.controller.OrgAdminsPojo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgadminsMapper {
    int countByExample(OrgadminsExample example);

    int deleteByExample(OrgadminsExample example);

    int deleteByPrimaryKey(OrgadminsKey key);

    int insert(OrgadminsKey record);

    int insertSelective(OrgadminsKey record);

    List<OrgadminsKey> selectByExample(OrgadminsExample example);

    int updateByExampleSelective(@Param("record") OrgadminsKey record, @Param("example") OrgadminsExample example);

    int updateByExample(@Param("record") OrgadminsKey record, @Param("example") OrgadminsExample example);

	List<OrgAdminsPojo> selectOrgAdmins(Integer oid);
}