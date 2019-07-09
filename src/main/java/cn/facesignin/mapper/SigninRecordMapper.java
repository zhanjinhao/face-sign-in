package cn.facesignin.mapper;

import cn.facesignin.pojo.SigninRecord;
import cn.facesignin.pojo.SigninRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SigninRecordMapper {
    int countByExample(SigninRecordExample example);

    int deleteByExample(SigninRecordExample example);

    int insert(SigninRecord record);

    int insertSelective(SigninRecord record);

    List<SigninRecord> selectByExample(SigninRecordExample example);

    int updateByExampleSelective(@Param("record") SigninRecord record, @Param("example") SigninRecordExample example);

    int updateByExample(@Param("record") SigninRecord record, @Param("example") SigninRecordExample example);
}