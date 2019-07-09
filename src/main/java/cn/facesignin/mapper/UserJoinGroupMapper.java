package cn.facesignin.mapper;

import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.UserJoinGroup;
import cn.facesignin.pojo.UserJoinGroupExample;
import cn.facesignin.pojo.controller.UserJoinGroupDetail;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserJoinGroupMapper {
    int countByExample(UserJoinGroupExample example);

    int deleteByExample(UserJoinGroupExample example);

    int insert(UserJoinGroup record);

    int insertSelective(UserJoinGroup record);

    List<UserJoinGroup> selectByExample(UserJoinGroupExample example);

    int updateByExampleSelective(@Param("record") UserJoinGroup record, @Param("example") UserJoinGroupExample example);

    int updateByExample(@Param("record") UserJoinGroup record, @Param("example") UserJoinGroupExample example);

    List<UserJoinGroupDetail> getUserJoinGroupDetail(String uid);

	List<MyGroup> getUserJoinGroupList(String uid);
}