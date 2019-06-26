package cc.aies.web.controller;

import cc.aies.web.beans.Menu;
import cc.aies.web.beans.PermUser;
import cc.aies.web.service.MenuService;
import cc.aies.web.utils.Constants;
import cc.aies.web.utils.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: 霍运浩
 * @Date: 2019/2/25 0025 0:50
 * @Description:
 */
@RestController
@RequestMapping("")
public class ErrorController {


    @Autowired
    MenuService menuService;
    //左边的菜单栏 根据用户的角色的不同  显示不同的菜单
    @RequestMapping(value = "/sidebar",method = RequestMethod.GET)
    public Msg sideMenu(){

        Session session = SecurityUtils.getSubject().getSession();
        PermUser permUser = (PermUser) session.getAttribute(Constants.SESSION_USER);
        List<Menu> menus= menuService.getUserMenu(permUser.getUserPermId());
        return Msg.success().add("msg",menus);
    }

    @RequestMapping(value = "/sidebar/{id}",method = RequestMethod.GET)
    public Msg sideMenuById(@PathVariable("id") String id){

        List<Menu> menus= menuService.getUserMenu(id);
        return Msg.success().add("msg",menus);
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public Msg errorHandle(){
        return Msg.auth().add("msg","大哥你越权了");
    }
    @RequestMapping(value = "/loginerror")
    public Msg loginHandle(){
        return Msg.fail().add("msg","请先登陆!");
    }
}
