package cc.aies.web.service;

import cc.aies.web.beans.*;
import cc.aies.web.dao.CompanyMapper;
import cc.aies.web.dao.PermUserMapper;
import cc.aies.web.dao.RoleMapper;
import cc.aies.web.utils.CommonUtils;
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
 * @time: 21:30
 */
@Service
public class PermService {
    @Autowired
    PermUserMapper permUserMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    CompanyMapper companyMapper;

    public List<PermUser> getAllUserInfo() {

        return permUserMapper.getAllUserInfo();
    }

    public PermUser selectUserById(String ids) {

        return permUserMapper.selectUserById(ids);
    }

    public List<String> getUserRoleIds(String userId) {
        return permUserMapper.getUserRoleIds(userId);
    }
    public List<Role> getUserRoleIds1(String userId) {
        return permUserMapper.getUserRoleIds1(userId);
    }

    public void addUserRole(String userId, String[] roleIds) {
        //在给用户分配角色的 先删除用户的所有角色关联
        permUserMapper.deleteUserRole(userId);
        Map<String,String> map=new HashMap<String,String>();
        map.put("userId",userId);
        for(String roleId:roleIds){
            map.put("roleId",roleId);
            permUserMapper.addUserRole(map);
        }
    }

    public void deleteBatch(List<String> de_ids) {
        for (String id :de_ids) {
            this.deleteId(id);
        }
    }


    public void deleteId(String ids) {
        permUserMapper.deleteUserById(ids);
        roleMapper.deleteUserRole(ids);
    }
    public void deleteIdguanlian(String ids) {
        roleMapper.deleteUserRole(ids);
    }

    public void updatePermUser(PermUser permUser) {
        permUserMapper.updateByPrimaryKeySelective(permUser);
    }

    public List<PermUser> getUserinfoByUserAccount(String userAccount) {

        PermUserExample permUserExample=new PermUserExample();
        PermUserExample.Criteria criteria=permUserExample.createCriteria();
        criteria.andUserAccountEqualTo(userAccount);
        return permUserMapper.selectByExample(permUserExample);
    }


    /**
     * 查询该用户下所有用户
     * @param companyId
     * @return
     */
    public List<PermUser> getAllUserInfoByCompanyId(String companyId) {

        List<PermUser> permUserList=permUserMapper.getAllUserInfoByCompanyId(companyId);
        for(PermUser permUser:permUserList){
            permUser.setCompanyName(companyMapper.selectByPrimaryKey(permUser.getCompanyId()).getCompanyName());
        }
        return permUserList;
    }
}
