package cc.aies.web.dao;

import cc.aies.web.beans.Company;
import cc.aies.web.beans.CompanyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {
    long countByExample(CompanyExample example);

    int deleteByExample(CompanyExample example);

    int deleteByPrimaryKey(String companyId);

    int insert(Company record);

    int insertSelective(Company record);

    List<Company> selectByExample(CompanyExample example);

    Company selectByPrimaryKey(String companyId);

    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> getListByParentId(String companyId);

    List<Company> getCompany();

    List<Company> getCompanyById(String companyId);

    List<Company> getCompantByParentId(String id);

    List<String> getCompanySonId(String companyId);

    List<Company> getAllComapnyWithNUll();

    List<Company> getSchoolByCompanyId(String companyId);

    List<Company> selectSonNode(String id);

    String getCompanyNameByCompanyId(String companyId);
}