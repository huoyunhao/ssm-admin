package cc.aies.web.controller;

import cc.aies.web.beans.Menu;
import cc.aies.web.service.MenuService;
import cc.aies.web.utils.CommonUtils;
import cc.aies.web.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 *
 * @author: 霍运浩
 * @date: 2018-09-04
 * @time: 23:58
 */
@RestController
@RequestMapping("")
public class MenuController {
    @Autowired
    MenuService menuService;
    //添加菜单
    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    public Msg addArticle(@RequestBody  @Valid Menu menu , BindingResult result)
    {
        if(menu.getMenuName()!=null && !menu.getMenuName().isEmpty())
        {
            if(result.hasErrors()) {
                //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
                Map<String, Object> map = new HashMap<String, Object>();
                List<FieldError> errors = result.getFieldErrors();
                for (FieldError fieldError : errors) {
                    System.out.println("错误的字段名：" + fieldError.getField());
                    System.out.println("错误信息：" + fieldError.getDefaultMessage());
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return Msg.fail().add("错误的字段", map);
            }else
            {
                menu.setMenuId(CommonUtils.getUUID());
                menuService.addMenu(menu);
            }
            return Msg.success().add("msg","添加成功");
        }else {
            return Msg.fail().add("msg","参数错误");
        }
    }
        //更新菜单
    @RequestMapping(value = "/menu/{id}",method = RequestMethod.PUT)
    public Msg updataRole(@RequestBody @Valid Menu menu , BindingResult bindingResult, @PathVariable("id") String id){
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
            Menu menu1=menuService.getMenuById(id);
            if(menu1!=null)
            {
                menu.setMenuId(id);
                menuService.updataMenu(menu);
                return Msg.success().add("msg","更新成功");
            }
            return  Msg.fail().add("msg","不存子id");
        }
    }

    /**
     * 查询所有菜单
     * @return
     */
    @RequestMapping(value = "/allMenu",method = RequestMethod.GET)
    public Msg getAllMenu(){

        List<Menu> menuList=menuService.getAllMenu();
        return Msg.success().add("msg",menuList);
    }
    /**
     * 查询菜单通过id
     */
    @RequestMapping(value = "/menu/{id}",method = RequestMethod.GET)
    public Msg getMenuById(@PathVariable("id") String id){

        Menu menu =menuService.getMenuById(id);
        return Msg.success().add("msg",menu);
    }
    /**
     * 删除菜单通过id
     */
    @RequestMapping(value = "/deleteMenu",method = RequestMethod.POST)
    public Msg deleteMenuById(@RequestBody List<String> ids){

        for(String id:ids){
            menuService.deleteMenuById(id);
        }
        return Msg.success().add("msg","删除成功");
    }

}