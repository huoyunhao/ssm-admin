package cc.aies.web.utils;

import cc.aies.web.beans.LogExample;
import cc.aies.web.dao.LogMapper;
import org.junit.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: qiuzp
 * @Date: 18-9-2 上午11:37
 * @Description:单独线程定时执行一些任务
 */
public class TimedTask  {

    @Autowired
    private LogMapper logMapper;
    /**
     * 定时删除一定的日志
     */
    public void logDelete() throws Exception {

        //获取一周前的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-7);
        Date pastTime = calendar.getTime();

        //删除一周前的日志
        LogExample logExample = new LogExample();
        LogExample.Criteria criteria = logExample.createCriteria();
        criteria.andCreateTimeBetween(pastTime,new Date());
        if(logMapper.deleteByExample(logExample)<=0){
            throw new Exception("delete log error!");
        }
    }

}
