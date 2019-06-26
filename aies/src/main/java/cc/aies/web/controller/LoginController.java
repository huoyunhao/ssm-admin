
package cc.aies.web.controller;

import cc.aies.web.beans.PermUser;
import cc.aies.web.service.PermService;
import cc.aies.web.service.UserLoginService;
import cc.aies.web.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @Auther: qiuzp
 * @Date: 2018/7/29 14:48
 * @Description:
 */

@Controller
@RequestMapping("/user")
public class   LoginController {
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    PermService permService;



    @ResponseBody
    @RequestMapping(value = "/permlogin",method = RequestMethod.POST)
    public Msg perLogin(@RequestBody Map<String,String> map1, HttpServletRequest request){
        String valueCode=(String)request.getSession().getAttribute("strCode");
        if(valueCode==null){
            return Msg.fail().add("msg","请先获取验证码!");
        }
            if(valueCode.equals(map1.get("value"))){
                if (map1.get("userAccount").equals("") || map1.get("userPass").equals("")) {
                    return Msg.fail().add("msg", "账号或密码参数错误!");
                } else {
                    PermUser permUser = new PermUser();
                    permUser.setUserAccount(map1.get("userAccount"));
                    permUser.setUserPass(map1.get("userPass"));
                    Map<String, Object> map = userLoginService.isPuserExistByAccountAndPass(permUser);
                    if (map == null) {
                        return Msg.fail().add("msg", "用户名或密码错误！");
                    } else {
                        return Msg.success().add("user", map);
                    }
                }
        }else{
            return Msg.fail().add("msg","验证码错误!");
        }
    }

    /**
     * 注册用户
     * @param permUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public Msg perSignup(@RequestBody PermUser permUser){
        if(permUser.getUserAccount().equals("") || permUser.getUserPass().equals("")){
            return Msg.fail().add("msg", "参数错误");
        }else{
            List<PermUser> permUser1=permService.getUserinfoByUserAccount(permUser.getUserAccount());
            if(permUser1.size()==0)
            {
                String userPermId=CommonUtils.getUUID();
                permUser.setUserPermId(userPermId);
                permUser.setUserPass(MD5Util.md5Encode(permUser.getUserPass()));
                //o 为永久账号
                permUser.setStatus("0");
                userLoginService.insertSignUp(permUser);
                String[] strings=new String[1];
                strings[0]= permUser.getRoleId();
                permService.addUserRole(userPermId,strings);
                return Msg.success().add("msg","添加成功");
            }
            else{
                return Msg.fail().add("msg","用户名已经存在");
            }
        }
    }
    //用户退出
    @ResponseBody
    @RequestMapping(value = "/permloginout",method = RequestMethod.GET)
    public Msg loginout(){
        //得到当前的登录对象
        Subject subject = SecurityUtils.getSubject();
        Session session = SecurityUtils.getSubject().getSession();
        PermUser permUser = (PermUser) session.getAttribute(Constants.SESSION_USER);
        //退出当前的登录对象
        subject.logout();
        return Msg.success().add("msg","用户退出");
    }



}

