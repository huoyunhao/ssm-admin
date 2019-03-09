package cc.aies.web.controller;

import cc.aies.web.beans.Company;
import cc.aies.web.beans.PermUser;
import cc.aies.web.service.CompanyService;
import cc.aies.web.utils.Constants;
import cc.aies.web.utils.JWT;
import cc.aies.web.utils.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * qiuzp
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    /**
     * 添加
     * @param company
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Msg classAdd(@RequestBody Company company){

        int ret = companyService.addCompany(company);


        if(ret == 0)
            return Msg.fail();
        else {
            return Msg.success();
        }
    }

    /**
     * select by primary Id
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg classById(@PathVariable("id") String companyId){

        System.out.println("请求机构ID：" + companyId);

        Company ret = companyService.CompanyByCompanyId(companyId);

        Msg msg = new Msg();
        return msg.success().add("company", ret);


    }

    /**
     * update
     * @param company
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Msg classUpdate(@RequestBody Company company){
        if(companyService.CompanyUpdate(company) == -2)
        {
            Msg msg= new Msg();
            msg.setCode("501");
            msg.setMsg("company not exist");
            return msg;
        }
        else{
            Msg msg = new Msg();
            return msg.success().add("updated", companyService.CompanyByCompanyId(company.getCompanyId()));
        }
    }

    /**
     * delete
     * @param
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg classDelete(@PathVariable("id") String companyId){


        if(companyService.CompanyDelete(companyId) == -2)
        {
            Msg msg = new Msg();
            msg.setCode("501");
            msg.setMsg("company not exist");
            return  msg;
        }
        else {
            return Msg.success();
        }
    }

    /**
     * 批量删除
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/compbatch",method = RequestMethod.POST)
    public Msg batchDelete(@RequestBody Map<String,Object> map){
        if(map==null || map.get("ids")==null){
            return Msg.fail().add("msg","参数错误!");
        }
        List<String> list = (List<String>) map.get("ids");
        if(list==null || list.size()==0){
            return Msg.fail().add("msg","ids[]为空!");
        }else{
            for(String id:list){
                if(id==null || id.trim().equals("")){
                    return Msg.fail().add("msg","id为空!");
                }else{
                    int f = companyService.CompanyDelete(id);
                    if(f!=0){
                        return Msg.fail().add("msg","id为"+id+"的值删除失败!");
                    }
                }
            }
            return Msg.success();
        }
    }

    /**
     * 获取所有公司/学校
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Msg getAllCompany(){
        List<Company> list  = companyService.getAllComapny();
        if(list==null || list.size()==0){
            return Msg.fail().add("msg","null");
        }else{
            return Msg.success().add("companys",list);
        }
    }

    /**
     * 获取该用户权限下学校列表
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/parentList",method = RequestMethod.GET)
    public Msg getCompanyByParentId(HttpServletRequest request){
        PermUser permUser = JWT.unsign(request.getHeader("token"),PermUser.class);
        if(permUser.getCompanyId().equals("1000"))
        {
            List<Company> companyList = companyService.getAllComapny();
            return Msg.success().add("msg",companyList);
        }else {
            List<Company> list = companyService.getCompantByParentId(permUser.getCompanyId());
            return Msg.success().add("companys", list);

        }

    }

    /**
     * 获取左上角的校区标头
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/headerSchool",method = RequestMethod.GET)
    public Msg getheaderSchool(HttpServletRequest request){
        PermUser permUser = JWT.unsign(request.getHeader("token"),PermUser.class);
        List<Company> companyList=companyService.getCompangyListByCompanyId(permUser.getCompanyId());
        return Msg.success().add("msg",companyList);
    }

    /**
     * company模糊查询
     */
    @ResponseBody
    @RequestMapping(value = "/fuzzy",method = RequestMethod.POST)
    public Msg getCompanyByFuzzyKey(@RequestBody Map<String,String> map){
        if(map.get("key")==null || map.get("key").trim().equals("")){
            return Msg.fail().add("msg","参数错误!");
        }
        List<Company> companies = companyService.getCoampanyByFuzzyName(map.get("key"));
        if(companies==null || companies.size()==0){
            return Msg.fail().add("companies","null");
        }else{
            return Msg.success().add("companies",companies);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/all/Userschool/{companyId}",method = RequestMethod.GET)
    public Msg getCompanyByUserRole(@PathVariable("companyId") String companyId){


        List<Company> companyList=companyService.getCompangyListByCompanyId(companyId);
        return Msg.success().add("msg",companyList);

    }


    @ResponseBody
    @RequestMapping(value = "/school/{id}",method = RequestMethod.GET)
    public Msg getSchoolByCompanyId(@PathVariable("id") String companyId){
        List<Company> companies = companyService.getSchoolByCompanyId(companyId);
        if(companies==null || companies.size()==0){
            return Msg.fail().add("msg","null");
        }
        return Msg.success().add("schools",companies);
    }

    /**
     * 根据机构ID获取该结构下的所有教育局
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/education",method = RequestMethod.GET)
    public  Msg getEducationByCommpanyId(HttpServletRequest request){
        PermUser permUser = JWT.unsign(request.getHeader("token"),PermUser.class);
        List<Company> companyList=companyService.getEducationByCommpanyId(permUser.getCompanyId());
        return Msg.success().add("msg",companyList);
    }
}
