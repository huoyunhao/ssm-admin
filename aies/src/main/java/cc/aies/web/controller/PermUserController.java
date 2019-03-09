package cc.aies.web.controller;

import cc.aies.web.beans.PermUser;
import cc.aies.web.beans.Role;
import cc.aies.web.beans.UserRole;
import cc.aies.web.service.PermService;
import cc.aies.web.utils.MD5Util;
import cc.aies.web.utils.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 *
 * @author: 霍运浩
 * @date: 2018-09-04
 * @time: 21:27
 */
@RestController
@RequestMapping("")
public class PermUserController {


    @Autowired
    PermService permService;

    /**
     * 分页查询所有用户信息
     * @param curPage
     * @return
     */
    @RequestMapping(value = "/User/{companyId}",method = RequestMethod.GET)
    public Msg getAllUserMsg(@RequestParam(value = "curPage",defaultValue = "1") Integer curPage,
                             @PathVariable("companyId") String companyId){

        PageHelper.startPage(curPage,20);
        List<PermUser> permUserList=permService.getAllUserInfoByCompanyId(companyId);
        PageInfo pageInfo=new PageInfo(permUserList,5);
        return Msg.success().add("msg",pageInfo);
    }
    /**
     * 批量删除用户
     * @param ids
     * @param
     * @return
     */
    @RequestMapping(value = "/permUser/{ids}",method = RequestMethod.DELETE)
    public Msg deleteUserMsgByuserid(@PathVariable("ids") String ids){

        if(ids!=null && !ids.trim().isEmpty()) {
            if (ids.contains("_")) {
                List<String> de_ids = new ArrayList<String>();
                String[] str_ids = ids.split("_");
                for (String string : str_ids) {
                    de_ids.add(string);
                }
                permService.deleteBatch(de_ids);
            } else {
                permService.deleteId(ids);
            }
            return Msg.success().add("msg", "删除成功");
        }
        return Msg.fail().add("msg","参数错误");
    }
    /**
     * 通过id查询用户信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "/permUser/{ids}",method = RequestMethod.GET)
    public Msg selectUserMsgByUserId(@PathVariable("ids") String ids){

//        System.out.println(ids);
//        System.exit(0);
        if(ids!=null&&!ids.isEmpty()){
            PermUser permUser=permService.selectUserById(ids);
            permUser.setUserPass("你不能查看");
            return Msg.success().add("msg",permUser);
        }else{
            return Msg.fail().add("msg","id不存在");
        }
    }
    /**
     * 通过id修改用户信息
     * @param
     * @return
     */
    @RequestMapping(value = "/permUserP/{id}",method = RequestMethod.PUT)
    public Msg updateUserMsgByUserId(@PathVariable("id") String id,@RequestBody PermUser permUser){
        if(id!=null&&!id.isEmpty()){

            permUser.setUserPermId(id);

            if(permUser.getUserPass()!=null && permUser.getUserPass()!="")
            {
                permUser.setUserPass(MD5Util.md5Encode(permUser.getUserPass()));
            }
            permService.updatePermUser(permUser);
            return Msg.success().add("msg","修改成功");
        }else{
            return Msg.fail().add("msg","id不存在");
        }
    }

    /**
     * 根据用户的id 得到用户的角色 返回一个数组
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userRole/{userId}",method = RequestMethod.GET)
    public Msg userRole(@PathVariable("userId") String userId){
        Map<String, List<Role>> stringListMap=new HashMap<String, List<Role>>();
        List<Role> list=permService.getUserRoleIds1(userId);
        stringListMap.put("roleIds",list);
        return Msg.success().add("msg",stringListMap);
    }
    /**
     * 为用户分配角色 传入用户的id和角色的id字符串
     * @return
     */
    @RequestMapping(value = "/assignRole",method = RequestMethod.POST)
    public Msg assignRole(@RequestBody UserRole userRole){

        if(userRole.getRoleId()!=null && userRole.getUserId()!=null
                &&!userRole.getRoleId().trim().isEmpty()&&!userRole.getRoleId().trim().isEmpty()){
            String[] roleIds=null;
            if(!userRole.getRoleId().contains("_")){
                userRole.setRoleId(userRole.getRoleId()+"_");
            }
            if(userRole.getRoleId().contains("_")) {
                roleIds = userRole.getRoleId().split("_");
            }
            permService.addUserRole(userRole.getUserId(),roleIds);
            return Msg.success().add("msg","分配成功");
        }
        return Msg.fail().add("msg","参数错误");
    }

}
