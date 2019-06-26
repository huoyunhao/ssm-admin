package cc.aies.web.service;


import cc.aies.web.beans.PermUser;
import cc.aies.web.beans.PermUserExample;
import cc.aies.web.dao.PermUserMapper;
import cc.aies.web.utils.Constants;
import cc.aies.web.utils.JWT;
import cc.aies.web.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther: qiuzp
 * @Date: 2018/7/29 14:12
 * @Description:
 */

@Service
public class UserLoginService{

    @Autowired
    private PermUserMapper permUserMapper;


    /**
     * 判断永久用户是否存在
     * 若存在，返回token用于验证身份，返回用户id,name
     * @param permUser
     * @return
     */
    public Map<String,Object> isPuserExistByAccountAndPass(PermUser permUser){
        PermUserExample permUserExample = new PermUserExample();
        PermUserExample.Criteria criteria = permUserExample.createCriteria();
        criteria.andUserAccountEqualTo(permUser.getUserAccount());
        String passWord=MD5Util.md5Encode(permUser.getUserPass());
        criteria.andUserPassEqualTo(passWord);
        criteria.andStatusEqualTo("0");
        //得到当前登录对象
        Subject subject=SecurityUtils.getSubject();
        //账号密码生成凭证
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(permUser.getUserAccount(),passWord);
        try {
            //设置记住我
            usernamePasswordToken.setRememberMe(true);
            //调用shiro登录函数 登录错误则跳转到AuthenticationException e
            subject.login(usernamePasswordToken);
            //获取独享的session对象
            Session session = subject.getSession();
            //通过用户名返回当前登录用户对象

            PermUser PermUser = this.getPermUserByUsername(permUser.getUserAccount());
            //将当前登录对象存入session
            session.setAttribute(Constants.SESSION_USER, PermUser);
        }catch (AuthenticationException e){
//            e.printStackTrace();
            // 如何不存在当前对象则返回null
            return null;
        }
        List<PermUser> list = permUserMapper.selectByExample(permUserExample);
        if(list.size()==0){
            return null;
        }else{
            Map<String,Object> map = new HashMap<>();
            String userId = list.get(0).getUserPermId();
            //设置token为4个小时有效
            String token = JWT.sign(list.get(0), 1000*60*60*14);
            //设置token为14个小时有效
            map.put("token", token);
            map.put("userId", userId);
            map.put("userName", list.get(0).getUserName());
            map.put("companyId",list.get(0).getCompanyId());
            return map;
        }
    }

    /**用户名查询用户的信息
     * 通过
     * @param username
     * @return
     */
    public PermUser getPermUserByUsername(String username){

        return permUserMapper.selectByAccount(username);

    }


    public void insertSignUp(PermUser permUser) {

        permUserMapper.insertSelective(permUser);

    }
}

