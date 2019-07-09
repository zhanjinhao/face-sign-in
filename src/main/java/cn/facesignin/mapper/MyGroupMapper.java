package cn.facesignin.mapper;

import cn.facesignin.pojo.MyGroup;
import cn.facesignin.pojo.MyGroupExample;
import cn.facesignin.pojo.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyGroupMapper {
    int countByExample(MyGroupExample example);

    int deleteByExample(MyGroupExample example);

    int deleteByPrimaryKey(Integer gid);

    int insert(MyGroup record);

    int insertSelective(MyGroup record);
    
    public List<User> getUsersInGroup(Integer gid);
    
    List<MyGroup> getGroupList(Integer oid);
    
    List<MyGroup> selectByExample(MyGroupExample example);

    MyGroup selectByPrimaryKey(Integer gid);

    int updateByExampleSelective(@Param("record") MyGroup record, @Param("example") MyGroupExample example);

    int updateByExample(@Param("record") MyGroup record, @Param("example") MyGroupExample example);

    int updateByPrimaryKeySelective(MyGroup record);

    int updateByPrimaryKey(MyGroup record);
    
    int getGidUsedNum(Integer gid);
}