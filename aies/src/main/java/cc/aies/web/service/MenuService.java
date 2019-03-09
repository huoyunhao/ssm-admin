package cc.aies.web.service;

import cc.aies.web.beans.Menu;
import cc.aies.web.beans.MenuPermission;
import cc.aies.web.beans.Role;
import cc.aies.web.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 *
 * @author: 霍运浩
 * @date: 2018-09-04
 * @time: 22:53
 */
@Service
public class MenuService {
    @Autowired
    MenuMapper menuDao;
    //得到菜单树
    public List<Menu> getTreeMenu() {
        List<Menu> menus = menuDao.getAllMenu();
        System.out.println(menus);
        return makeMenu(menus);
//        return menus;
    }
    //树形菜单
    private List<Menu> makeMenu(List<Menu> menus) {
        Map<String, Menu> menuMap = new HashMap<String, Menu>(menus.size());
        List<Menu> parentMenu = new ArrayList<Menu>();
        for (Menu menu : menus) {
            menuMap.put(menu.getMenuId(), menu);
            if (menu.getMenuParentId() == null || menu.getMenuParentId().trim().isEmpty()) {
                parentMenu.add(menu);
            }
        }
        for (Menu menu : menus) {
            if (menu.getMenuParentId() != null || !menu.getMenuParentId().trim().isEmpty()) {
                Menu parent = menuMap.get(menu.getMenuParentId());
                if (parent != null ) {
                    parent.getChildren().add(menu);

                }
            }
        }
        return parentMenu;
    }
    //    <!--得到角色所对应的菜单id-->
    public List<MenuPermission> getMenuPerms() {
        return menuDao.getMenuPerms();
    }
    //通过菜单id得到菜单信息
    public Menu getMenuById(String id) {
        return menuDao.getMenuById(id);
    }
    //更新菜单
    public void updataMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }
    //添加菜单
    public void addMenu(Menu menu) {
        menuDao.addMenu(menu);
    }
    //通过用户的id得到用户的左边菜单
    public List<Menu> getUserMenu(String userId) {
        List<Menu> menus = menuDao.getUserMenu(userId);
        return makeMenu(menus);
    }

    public List<Menu> getAllMenu() {
        return menuDao.getAllMenu();
    }

    public void deleteMenuById(String id) {
        menuDao.deleteMenuById(id);
        menuDao.deleteRoleMenuById(id);
    }

    public List<Role> getRoleLogin(String userPermId) {

        return menuDao.getRoleLogin(userPermId);
    }

    public List<String> getrolesuperAdmin() {

        return menuDao.getrolesuperAdmin();
    }

    public List<String> getrolecityAdmin() {
        return menuDao.getrolecityAdmin();
    }

    public List<String> getroleschoolAdmin() {

        return menuDao.getroleschoolAdmin();
    }

    public Role getRoleById(String roleId) {

        return  menuDao.getRoleById(roleId);
    }
}
