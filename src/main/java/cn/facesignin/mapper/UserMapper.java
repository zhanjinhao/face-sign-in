package cn.facesignin.mapper;

import cn.facesignin.pojo.User;
import cn.facesignin.pojo.UserExample;
import cn.facesignin.pojo.controller.UserActivity;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<UserActivity> getUserActivityNotInGroup(String uid);
    List<UserActivity> getUserActivityInGroup(String uid, Integer gid);
}