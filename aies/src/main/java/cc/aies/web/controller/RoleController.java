package cc.aies.web.controller;

import cc.aies.web.beans.Menu;
import cc.aies.web.beans.PermUser;
import cc.aies.web.beans.Role;
import cc.aies.web.beans.RoleMenu;
import cc.aies.web.service.MenuService;
import cc.aies.web.service.RoleService;
import cc.aies.web.utils.CommonUtils;
import cc.aies.web.utils.Constants;
import cc.aies.web.utils.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by Intellij IDEA.
 *
 * @author: 霍运浩
 * @date: 2018-09-04
 * @time: 22:52
 */
@RestController
@RequestMapping("")
public class RoleController {

    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;
    /**
     * 得到所有角色
     * @return
     */
    @RequestMapping(value = "/allRoles",method = RequestMethod.GET)
    public Msg getAllRoles(){
        return Msg.success().add("msg",roleService.getAllRoles());
    }
    /**
     * 添加一个角色
     * @param role
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/role",method = RequestMethod.POST)
    public Msg addRole(@RequestBody @Valid Role role , BindingResult bindingResult){
        if(role.getRoleName()!=null && !role.getRoleName().isEmpty() ){
            if(bindingResult.hasErrors()){
                Map<String,Object> map=new HashMap<String,Object>();
                List<FieldError> errors=bindingResult.getFieldErrors();
                for (FieldError fieldError:errors){
                    map.put(fieldError.getField(),fieldError.getDefaultMessage());
                }
                return Msg.fail().add("fieldMsg",map);
            }else{
                role.setRoleId(CommonUtils.getUUID());
                roleService.addRoles(role);
                return Msg.success().add("msg","添加成功");
            }
        }else{
            return Msg.fail().add("msg","参数错误");
        }
    }
    /**
     * 通过id获取角色
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/{id}",method = RequestMethod.GET)
    public Msg getRoleById(@PathVariable("id") String id){
        Role role=roleService.getRoleById(id);
        return Msg.success().add("msg", role);
    }



    //更新角色
    @RequestMapping(value = "/role/{id}",method = RequestMethod.PUT)
    public Msg updataRole(@RequestBody @Valid Role role ,BindingResult bindingResult,@PathVariable("id") String id){
        if(bindingResult.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<String, Object>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("错误的字段", map);
        }else
        {
            Role role1=roleService.getRoleById(id);
            if(role1!=null)
            {
                role.setRoleId(id);
                roleService.updataRole(role);
                return Msg.success().add("msg","更新成功");
            }
            return  Msg.success().add("msg","不存子id");
        }

    }
    //    通过角色的id得到角色对应的的菜单id数组
    @RequestMapping(value = "roleMenu/{id}",method = RequestMethod.GET)
    public Msg roleMenu(@PathVariable("id") String id){
        List<Menu> list=roleService.getRoleMenuIds(id);

        return Msg.success().add("msg",list);
    }
    //    为角色分配菜单 传入角色的id  和菜单的ids数组
    @RequestMapping(value = "/assignMenu",method = RequestMethod.POST)
    public Msg assignMenu(@RequestBody @Valid RoleMenu roleMenu) {
        String[] menuIds=null;
        if(!roleMenu.getMenuId().contains("_")){
            roleMenu.setMenuId(roleMenu.getMenuId()+"_");
        }
        if(roleMenu.getMenuId().contains("_")) {
            menuIds = roleMenu.getMenuId().split("_");
        }
        roleService.addRoleMenu(roleMenu.getRoleId(), menuIds);
        return Msg.success().add("msg","分配成功");
    }
    //    得到树形菜单
    @RequestMapping(value = "/mengList",method = RequestMethod.GET)
    public List<Menu> menuList() {
        return menuService.getTreeMenu();
    }

    /**
     * 删除角色通过角色id
     * @param roleId
     * @return
     */
    @RequestMapping(value = "role/{roleId}",method = RequestMethod.DELETE)
    public Msg deleteRole(@PathVariable("roleId") String roleId ){
        roleService.deleteRole(roleId);
        return Msg.success().add("msg","删除成功");
    }


}
