package cc.aies.web.service;

import cc.aies.web.beans.Log;
import cc.aies.web.beans.LogExample;
import cc.aies.web.dao.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: qiuzp
 * @Date: 18-8-31 下午8:04
 * @Description:
 */
@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    /**
     * 无条件获取全部日志,按时间倒叙排列
     * @return
     */
    public List<Log> getAllLogs(){
        List<Log> logs = logMapper.selectLogsOrderByCreateTime();
        if(logs==null || logs.size()==0){
            return null;
        }else{
            return logs;
        }
    }

    /**
     * 获取某一时间段的日志
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Log> getLogsBetween(Date startTime,Date endTime){

        List<Log> logs = logMapper.selectLogsBetweenDateOrderByCreateTime(startTime,endTime);
        if(logs==null || logs.size()==0){
            return null;
        }else{
            return logs;
        }
    }

    /**
     * 获取特定Ip产生的日志
     */
    public List<Log> getLogsByIp(String ip){
        List<Log> logs = logMapper.selectLogsByIp(ip);
        System.out.println("logssize:==================>"+logs.size());
        System.out.println(logs.toArray().toString()+"===============<");
        return logs;
    }
}
