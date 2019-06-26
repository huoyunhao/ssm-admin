package cc.aies.web.dao;

import cc.aies.web.beans.Menu;
import cc.aies.web.beans.MenuPermission;
import cc.aies.web.beans.Role;

import java.util.List;

public interface MenuMapper {

        //
    List<Menu> getAllMenu();

    List<MenuPermission> getMenuPerms();

    Menu getMenuById(String id);

    void updateMenu(Menu menu);

    void addMenu(Menu menu);
    //得到用户的菜单
    List<Menu> getUserMenu(String userId);

    void deleteMenuById(String id);

    void deleteRoleMenuById(String id);

    List<Role> getRoleLogin(String userPermId);

    List<String> getrolesuperAdmin();

    List<String> getrolecityAdmin();

    List<String> getroleschoolAdmin();

    Role getRoleById(String roleId);
}