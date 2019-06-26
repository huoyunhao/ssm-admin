package cc.aies.web.service;


import cc.aies.web.beans.Menu;
import cc.aies.web.beans.Role;
import cc.aies.web.dao.RoleMapper;
import cc.aies.web.fifter.MyShiroFilterFactoryBean;
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
public class RoleService {

    @Autowired
    RoleMapper roleDao;
    @Autowired
    MyShiroFilterFactoryBean myShiroFilterFactoryBean;
    //得到所有角色信息
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
    //添加一个角色
    public void addRoles(Role role) {
        roleDao.insertRole(role);
    }
    //通过角色的id得到角色
    public Role getRoleById(String id) {
        return roleDao.getRoleById(id);
    }
    //更新角色
    public void updataRole(Role role) {
        roleDao.updateRole(role);
    }
    //通过角色的id得到角色的菜单数组
    public List<Menu> getRoleMenuIds(String id) {
        return makeMenu(roleDao.getRoleMenuIds(id));
    }
    //树形菜单
    private List<Menu> makeMenu(List<Menu> menus) {
        System.out.println(menus.size());
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

    //传入角色的id  和菜单的id数组
    public void addRoleMenu(String roleId, String[] menuIds) {
        //同样的套路  删除角色的所属的所有菜单
        roleDao.deleteRoleMenu(roleId);
        Map<String, String> param = new HashMap<String, String>();
        param.put("roleId", roleId);
        for (String menuId : menuIds) {
            param.put("menuId", menuId);
            roleDao.addRoleMenu(param);
        }
//        调用过滤工厂的方法 进行权限的更新
        myShiroFilterFactoryBean.update();
    }

    public void deleteRole(String id) {
        roleDao.deleteRole(id);
        roleDao.deleteRoleMenu(id);
    }
}
