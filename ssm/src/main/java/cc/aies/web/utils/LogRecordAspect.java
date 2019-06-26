package cc.aies.web.utils;


import cc.aies.web.beans.Log;
import cc.aies.web.dao.LogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: qiuzp
 * @Date: 2018/8/21
 * @Description:用于日志记录
 */
@Aspect
@Component
public class LogRecordAspect {
    @Autowired
    private LogMapper logMapper;
    private long startTime;
    private long endTime;
    private Log log = new Log();

    /**
     * 还存在日志插入主建冲突问题
     * @param joinPoint
     */

    @Before(value = "execution(* cc.aies.web.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        HttpServletRequest request = getHttpServletRequest();
        startTime = System.currentTimeMillis();
        log.setCreateTime(new Date());
        log.setIp(CommonUtils.getIpAddress(request));
        log.setUrl(request.getRequestURI());
        if(request.getHeader("token")!=null) {
            //log.setOptionPersion(JWT.unsign();
        }
        log.setOptionPersion("test");
        log.setPosts(Arrays.asList(joinPoint.getArgs()).toString());
        log.setUserAgent(request.getHeader("User-Agent"));
    }


    /**
     * 后置通知
     */
    @After(value = "execution(* cc.aies.web.controller.*.*(..))")
    public void after(){
        endTime = System.currentTimeMillis();
        log.setLogValue((endTime-startTime)+"ms");
    }

    /**
     * 返回通知
     */
    @AfterReturning(value = "execution(* cc.aies.web.controller.*.*(..))",returning = "result")
    public void afterReturning(Msg result) {
        log.setLogId(CommonUtils.getUUID());
        log.setGets(result.getData().toString());
      //  System.out.println("LOG============>>>>>"+log.toString());
        try {
            if(!(logMapper.insert(log) >0)){
                System.out.println("日志记录失败!");
            }
        }catch (Exception e){
            System.out.println("log error!");
        }

    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "execution(* cc.aies.web.*.*.*(..))",throwing = "e")
    public void afterThrowing(JoinPoint joinpoint,Exception e){

            System.out.println("ERROR====================================");
            System.out.println("ERROR=====================================");
            log.setLogId(CommonUtils.getUUID());
            log.setCreateTime(new Date());
            log.setLogValue("Error:"+log.getUrl()+"-"+joinpoint.getSignature().getName());
            logMapper.insert(log);
            System.out.println("ErrorLog:=========>"+log.toString());

    }

    /**
     * 获取request;
     * @return
     */
    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

}
