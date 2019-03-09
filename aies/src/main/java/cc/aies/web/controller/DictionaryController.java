package cc.aies.web.controller;

import cc.aies.web.beans.Dictionary;
import cc.aies.web.service.DictionaryService;
import cc.aies.web.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author: 霍运浩
 * @date: 2018-09-23
 * @time: 23:10
 */
@RestController
@RequestMapping("")
public class DictionaryController {


    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping(value = "dicP",method = RequestMethod.POST)
    public Msg addDic(@RequestBody Dictionary dictionary){

        dictionaryService.addDic(dictionary);
        return Msg.success().add("msg","添加成功");
    }
    @RequestMapping(value = "dicP/{id}",method = RequestMethod.PUT)
    public Msg updateDic(@PathVariable("id") String id,@RequestBody Dictionary dictionary){

        dictionary.setDictionaryId(id);
        dictionaryService.updateDic(dictionary);
        return Msg.success().add("msg","更新成功");
    }

    @RequestMapping(value = "dicG/{id}",method =RequestMethod.GET)
    public Msg getDicById(@PathVariable("id") String id){
        return Msg.success().add("msg",dictionaryService.getDic(id));
    }

    /**
     * 查询所有父类的dic
     * @return
     */
    @RequestMapping(value = "dicPa",method = RequestMethod.GET)
    public Msg getDicPa(){
        List<Dictionary> dictionaryList=dictionaryService.getDicPa();
        return Msg.success().add("msg",dictionaryList);
    }
    /**
     * 获取父字典下的子菜单
     */
    @RequestMapping(value = "dicSon/{id}",method = RequestMethod.GET)
    public Msg getSonPa(@PathVariable("id") String id){
        List<Dictionary> dictionaryList=dictionaryService.getSonPa(id);
        return Msg.success().add("msg",dictionaryList);
    }
    /**
     * 删除字典通过字典id
     */
    @RequestMapping(value = "dicD/{id}",method =RequestMethod.DELETE)
    public Msg deleteDic(@PathVariable("id") String id){

            dictionaryService.deleteDic(id);
            return Msg.success().add("msg","删除成功");
    }
    /**
     * 获取字典树
     */
    @RequestMapping(value = "dicTree",method = RequestMethod.GET)
    public Msg getTreeDic(){
        List<Dictionary> dictionaryList=dictionaryService.getTreeDic();
        return Msg.success().add("msg",dictionaryList);
    }
}
