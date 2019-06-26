package cc.aies.web.service;

import cc.aies.web.beans.Company;
import cc.aies.web.beans.CompanyExample;
import cc.aies.web.dao.CompanyMapper;
import cc.aies.web.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * liyang
 *
 */
@Service
public class CompanyService extends BaseService {
    @Autowired
    CompanyMapper companyMapper;

    /**
     * 添加团体
     *
     * @param company
     * @return -1 - company exist
     */
    public int addCompany(Company company) {
        CommonUtils commonUtils = new CommonUtils();

        Date date = new Date();
        company.setCreateTime(date);

        do {
            company.setCompanyId(commonUtils.getUUID());
        } while (companyMapper.selectByPrimaryKey(company.getCompanyId()) != null);

        return companyMapper.insert(company);
    }

    /**
     * 根据 uuid 查找团体
     *
     * @param company_id
     * @return
     */
    public Company CompanyByCompanyId(String company_id) {

        return companyMapper.selectByPrimaryKey(company_id);
    }

    /**
     * 更新团体数据
     *
     * @param company
     * @return
     */
    public int CompanyUpdate(Company company) {

        return companyMapper.updateByPrimaryKey(company);
    }

    /**
     * 删除团体
     * 是否应该删除？
     *
     * @param companyId
     * @return 0 - success
     * 1 - fail
     * -2 - company not exist
     */

    public int CompanyDelete(String companyId) {

        if (companyMapper.selectByPrimaryKey(companyId) == null) {
            return -2;
        }
        if (companyMapper.deleteByPrimaryKey(companyId) != 0)
            return 0;
        else
            return 1;
    }

    /**
     * 获取所有学校/团体
     *
     * @return
     */
    public List<Company> getAllComapny() {
        return companyMapper.selectByExample(null);
    }

    /**
     * 通过parentId获取公司
     *
     * @param id
     * @return
     */
    public List<Company> getCompantByParentId(String id) {

        return companyMapper.getCompantByParentId(id);
    }

    private List<Company> makeTreeCompany(String id) {

        List<Company> companyList1 = new ArrayList<>();

        Company company = companyMapper.selectByPrimaryKey(id);
        if (company.getParentId() != null) {
            //子菜单
            companyList1.add(company);

        } else {
            //父菜单

            CompanyExample companyExample = new CompanyExample();
            CompanyExample.Criteria criteria = companyExample.createCriteria();
            criteria.andParentIdEqualTo(company.getCompanyId());
            companyList1 = companyMapper.selectByExample(companyExample);
            companyList1.add(0, company);
        }
        return companyList1;

    }

    /**
     * 学校/团体模糊查询
     */
    public List<Company> getCoampanyByFuzzyName(String key) {
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria = companyExample.createCriteria();
        if (key.matches("[1-9]{1,}")) {
            //通过学校编号查找
            criteria.andCompanyNumLike(key);
        } else {
            //通过学校名查找
            criteria.andCompanyNameLike(key);
        }
        return companyMapper.selectByExample(companyExample);
    }

    public List<Company> getCompantById(String companyId) {
//        //得到所有的公司列表
//        List<Company> companyList = companyMapper.getCompany();
//        //如果是超级管理员的话 返回所有列表
//        if (companyId.equals("1000")) {
//            return companyList;
//        }
//        List<Company> companyListResult = new ArrayList<>();
//        Company company = companyMapper.getCompanyById(companyId);
//        if (company.getParentId().equals("") || company.getParentId() == null) {
//
//
//            List<Company>  listparentReuslt =companyMapper.getListByParentId(companyId);
//            return listparentReuslt;
//
//        } else {
//
//            companyListResult.add(company);
//            return companyListResult;
//
//        }
        return null;

    }

    public List<Company> getCompanyById(String companyId) {

        return  companyMapper.getCompanyById(companyId);
    }

    public List<Company> getAllComapnyWithNUll() {

        return companyMapper.getAllComapnyWithNUll();
    }

    public Company getCompanyByCompanyId(String companyId) {
        return companyMapper.selectByPrimaryKey(companyId);
    }

    public List<Company> getSchoolByCompanyId(String companyId){
        return companyMapper.getSchoolByCompanyId(companyId);
    }



    List<Company> companyList=new ArrayList<>();
    private  void getRecursionNode(String id){
        //根据id 查询出改节点的信息
        Company company=companyMapper.selectByPrimaryKey(id);
        //如何该节点不为空
        if(company!=null)
        {
            //将该节点添加到list数组中
            companyList.add(company);
            //查询该节点下面还有没有子节点
            List<Company> dictionaryList=companyMapper.selectSonNode(id);
            for(Company company1:dictionaryList)
            {
                if(company1.getCompanyId()!=null){
                    getRecursionNode(company1.getCompanyId());
                }

            }
        }
    }
    /**
     * 获取
     * @param companyId
     * @return
     */

    public synchronized List<Company> getCompangyListByCompanyId(String companyId) {

        companyList.clear();
        this.getRecursionNode(companyId);
        System.out.println("+++++++++++++++++++++++++"+companyList.size());
        return companyList;

    }
    //得到该机构下的教育局
    public List<Company> getEducationByCommpanyId(String companyId) {

        Company company=companyMapper.selectByPrimaryKey(companyId);
        //如何这是顶级节点
        List<Company> companyListnode=new ArrayList<>();
        if(company.getParentId().equals(""))
        {
            companyListnode.add(company);
            List<Company> companyList1=companyMapper.selectSonNode(companyId);
            for(Company company1:companyList1)
            {
                companyListnode.add(company1);
            }
            return companyListnode;
        }
        else {
            companyListnode.clear();
            companyListnode.add(company);
            return companyListnode;
        }


    }

    public String getCompanyNameByCompanyId(String companyId) {

        return companyMapper.getCompanyNameByCompanyId(companyId);
    }
}
