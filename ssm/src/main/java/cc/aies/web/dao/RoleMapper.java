package cc.aies.web.dao;

import cc.aies.web.beans.Menu;
import cc.aies.web.beans.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 *
 * @author: 霍运浩
 * @date: 2018-09-04
 * @time: 23:02
 */
public interface RoleMapper {
    //得到所有的角色信息
    List<Role> getAllRoles();
    //添加一个角色
    void insertRole(Role role);
    //通过用户的id得到用户的角色
    Role getRoleById(String id);
    //更新用户的角色
    void updateRole(Role role);
    //通过角色的id得到菜单id
    List<Menu> getRoleMenuIds(String id);
    //删除角色关联的菜单
    void deleteRoleMenu(String roleId);
    //为角色分配权限
    void addRoleMenu(Map param);

    void deleteRole(String id);

    void deleteUserRole(String ids);
}
