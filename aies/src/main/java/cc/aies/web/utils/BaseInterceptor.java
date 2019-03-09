package cc.aies.web.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: qiuzp
 * @Date: 2018/7/28 22:09
 * @Description:
 */
public class BaseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        System.out.println("interceptor...");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //System.out.println(CommonUtils.readJSONString(request)+"===================>");
       //用token进行登录身份验证
        /*if(token==null){
            response.setStatus(403);
            request.getRequestDispatcher("/base/autherror").forward(request, response);
            return false;
        }else{
            PermUser permUser = JWT.unsign(token, PermUser.class);
            if(permUser==null){
                out = response.getWriter();
                out.print("会话过期，请重新登陆！");
                return false;
            }else{
                System.out.println("student login...");
                return true;
            }
        }*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
