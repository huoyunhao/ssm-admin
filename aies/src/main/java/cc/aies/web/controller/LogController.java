package cc.aies.web.controller;

import cc.aies.web.beans.Log;
import cc.aies.web.service.LogService;
import cc.aies.web.utils.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: qiuzp
 * @Date: 18-8-31 20:04
 * @Description:
 */
@Controller
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 获取全部日志
     * @param pn
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logs",method = RequestMethod.GET)
    public Msg getReviews(@RequestParam(value = "pn",defaultValue = "1") int pn){
        //每次传10条数据
        PageHelper.startPage(pn, 10);
        List<Log> list = logService.getAllLogs();
        PageInfo pageInfo = new PageInfo(list, 5);
        return Msg.success().add("pageinfo", pageInfo);
    }

    /**
     * 获取特定时间段日志
     * @param pn
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logs/between",method = RequestMethod.POST)
    public Msg getLogsBtweenDate(@RequestParam(value = "pn",defaultValue = "1") int pn,
                                     @RequestBody Map<String,Date> map){
        Date startTime = map.get("startTime");
        Date endTime = map.get("endTime");
        if(startTime==null || endTime==null){
            return Msg.fail().add("msg","Date error!");
        }
        PageHelper.startPage(pn,10);
        List<Log> logs = logService.getLogsBetween(startTime,endTime);
        PageInfo pageInfo = new PageInfo(logs,5);
        return Msg.success().add("pageinfo",pageInfo);
    }


    /**
     * 获取特定Ip日志
     */
    @ResponseBody
    @RequestMapping(value = "/logs/{ip}/{pn}",method = RequestMethod.GET)
    public Msg getLogsByIp(@PathVariable("pn") int pn,
                           @PathVariable("ip") String ip){
        if(ip==null || ip.trim().equals("")){
            return Msg.fail().add("msg","参数错误!");
        }else{
            PageHelper.startPage(pn,10);
            System.out.println("ip:=====>input:"+ip);
            List<Log> logs = logService.getLogsByIp(ip);
            System.out.println(logs.size()+"============size...");
            PageInfo pageInfo = new PageInfo(logs,5);
            return Msg.success().add("pageinfo",pageInfo);
        }
    }
}
