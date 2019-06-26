package cc.aies.web.dao;

import cc.aies.web.beans.PermUser;
import cc.aies.web.beans.PermUserExample;
import cc.aies.web.beans.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermUserMapper {
    long countByExample(PermUserExample example);

    int deleteByExample(PermUserExample example);

    int deleteByPrimaryKey(String userPermId);

    int insert(PermUser record);

    int insertSelective(PermUser record);

    List<PermUser> selectByExample(PermUserExample example);

    PermUser selectByPrimaryKey(String userPermId);

    int updateByExampleSelective(@Param("record") PermUser record, @Param("example") PermUserExample example);

    int updateByExample(@Param("record") PermUser record, @Param("example") PermUserExample example);

    int updateByPrimaryKeySelective(PermUser record);

    int updateByPrimaryKey(PermUser record);

    /**
     * 此后为新添加方法
     */
    PermUser selectByAccount(String userAccount);

    List<PermUser> getAllUserInfo();

    PermUser selectUserById(String ids);

    List<String> getUserRoleIds(String userId);

    List<Role> getUserRoleIds1(String userId);
    void addUserRole(Map<String,String> map);

    void deleteUserRole(String userId);

    void deleteUserById(String ids);

    List<PermUser> getAllteacherInfoExecl(String examid);


    List<PermUser> getTempJudgeTeacher(String mergeid);

    List<PermUser> getAllUserInfoBy(String companyId);

    List<PermUser> getAllUserInfoByAll(List<String> companyId);

    List<PermUser> getAllUserInfoByCompanyId(String companyId);
}