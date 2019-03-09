package cc.aies.web.dao;

import cc.aies.web.beans.PermRole;
import cc.aies.web.beans.PermRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermRoleMapper {
    long countByExample(PermRoleExample example);

    int deleteByExample(PermRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(PermRole record);

    int insertSelective(PermRole record);

    List<PermRole> selectByExample(PermRoleExample example);

    PermRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") PermRole record, @Param("example") PermRoleExample example);

    int updateByExample(@Param("record") PermRole record, @Param("example") PermRoleExample example);

    int updateByPrimaryKeySelective(PermRole record);

    int updateByPrimaryKey(PermRole record);
}