package cn.facesignin.mapper;

import cn.facesignin.pojo.DataDict;
import cn.facesignin.pojo.DataDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDictMapper {
    int countByExample(DataDictExample example);

    int deleteByExample(DataDictExample example);

    int insert(DataDict record);

    int insertSelective(DataDict record);

    List<DataDict> selectByExample(DataDictExample example);

    int updateByExampleSelective(@Param("record") DataDict record, @Param("example") DataDictExample example);

    int updateByExample(@Param("record") DataDict record, @Param("example") DataDictExample example);
}