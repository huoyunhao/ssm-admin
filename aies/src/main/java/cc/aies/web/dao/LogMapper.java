package cc.aies.web.dao;

import cc.aies.web.beans.Log;
import cc.aies.web.beans.LogExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LogMapper {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(String logId);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    //获取全部日志
    List<Log> selectLogsOrderByCreateTime();

    //获取特定时间段的日志
    List<Log> selectLogsBetweenDateOrderByCreateTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //获取特定Ip
    List<Log> selectLogsByIp(@Param("ip") String ip);
}