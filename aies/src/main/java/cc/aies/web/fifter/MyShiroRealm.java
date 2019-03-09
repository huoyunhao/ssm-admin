package cc.aies.web.fifter;

import cc.aies.web.beans.PermUser;
import cc.aies.web.service.PermService;
import cc.aies.web.service.UserLoginService;
import cc.aies.web.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    UserLoginService userLoginService;
    @Autowired
    PermService permService;
    //权限验证 进行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限验证");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
//        重session得到已经登录的用户对象
        PermUser permUser = (PermUser) session.getAttribute(Constants.SESSION_USER);
//        通过用户的id 得到用户所有的角色id数组
        List<String> roleIds = permService.getUserRoleIds(permUser.getUserPermId());
//        如何用户存在角色 则吧角色遍历出来 给授权对象
        if (roleIds != null && roleIds.size() > 0) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (String roleId : roleIds) {
                info.addRole(roleId);
            }
            return info;
        }
        return null;
    }
    //登录认证，进行验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username =(String) authenticationToken.getPrincipal();
        PermUser permUser=userLoginService.getPermUserByUsername(username);
        if(permUser!=null){
            return new SimpleAuthenticationInfo(username,permUser.getUserPass(),null,getName());
        }
        return null;
    }
}