package cc.aies.web.utils;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final Logger log = Logger.getLogger(ExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Msg handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return Msg.success().add("msg", "could_not_read_json");

    }

   /**
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Msg handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return Msg.auth().add("msg", "requestMethodNotSupporte");

    }

    /**
     * 404-NOT_FOUND
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Msg handlerNotFoundException(NoHandlerFoundException  e) {
        e.printStackTrace();
        return Msg.notFound().add("msg", "not found");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Msg Exception(Exception e) {
         e.printStackTrace();
        Msg msg = new Msg();
        msg.setCode("500");
        //当发生未知异常时，通过邮件通知管理人员;
        try {
            /*emailService.sendEmail("==="+e.getLocalizedMessage()+"===>"
                    +CommonUtils.getExceptionAllinformation(e));*/
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return msg.add("msg", "服务器内部错误!我们会尽快处理,谢谢");
    }

}