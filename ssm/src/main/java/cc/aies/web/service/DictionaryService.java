package cc.aies.web.service;

import cc.aies.web.beans.Dictionary;
import cc.aies.web.beans.DictionaryExample;
import cc.aies.web.dao.DictionaryMapper;
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
 * @date: 2018-09-23
 * @time: 23:13
 */
@Service
public class DictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;
    public void addDic(Dictionary dictionary) {
        dictionary.setDictionaryId(CommonUtils.getUUID());
        dictionaryMapper.insertSelective(dictionary);
    }

    public void updateDic(Dictionary dictionary) {
        dictionaryMapper.updateByPrimaryKeySelective(dictionary);
    }

    public Dictionary getDic(String id) {
       return dictionaryMapper.selectByPrimaryKey(id);
    }
//查询所有父类的dic
    public List<Dictionary> getDicPa() {
        DictionaryExample dictionaryExample=new DictionaryExample();
        DictionaryExample.Criteria criteria=dictionaryExample.createCriteria();
        criteria.andParentIdEqualTo("");
        return dictionaryMapper.selectByExample(dictionaryExample);
    }

    public List<Dictionary> getSonPa(String id) {
        DictionaryExample dictionaryExample=new DictionaryExample();
        DictionaryExample.Criteria criteria=dictionaryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        return dictionaryMapper.selectByExample(dictionaryExample);

    }

    /**
     * 获取所有字典
     * @return
     */
    public List<Dictionary> getAllDic(){
        return dictionaryMapper.selectByExample(null);
    }

    public List<Dictionary> getTreeDic() {

        List<Dictionary> dictionaryList=this.getAllDic();
        return this.makeTreeDic(dictionaryList);
        //获取所有字典
//
    }
    public List<Dictionary> makeTreeDic(List<Dictionary> dictionaryList){


        Map<String,Dictionary> stringDictionaryMap=new HashMap<>(dictionaryList.size());
//        //获取父字典
        List<Dictionary> dictionaryParentList=new ArrayList<>();

        for(Dictionary dictionary:dictionaryList){
            stringDictionaryMap.put(dictionary.getDictionaryId(),dictionary);
            if(dictionary.getParentId()==null || dictionary.getParentId().trim().isEmpty())
            {
                dictionaryParentList.add(dictionary);
            }
        }
        for(Dictionary dictionary:dictionaryList){

            if(dictionary.getParentId()!="" || !dictionary.getParentId().trim().isEmpty()){
                Dictionary parent=stringDictionaryMap.get(dictionary.getParentId());
                if(parent!=null)
                {
                    parent.getChildrenList().add(dictionary);
                }

            }
        }
        return dictionaryParentList;
    }

    /**
     * 查询出改字典所有的子字典
     */
    List<Dictionary> dictionaryDeList=new ArrayList<>();
    private void selectAllSonPa(String id){
        Dictionary dictionary=dictionaryMapper.selectByPrimaryKey(id);
        dictionaryDeList.add(dictionary);
        //查询这个字典的子字典
            DictionaryExample example=new DictionaryExample();
            DictionaryExample.Criteria criteria=example.createCriteria();
            criteria.andParentIdEqualTo(dictionary.getDictionaryId());
            List<Dictionary> dictionaryList=dictionaryMapper.selectByExample(example);
            for(Dictionary dictionary1:dictionaryList){
                selectAllSonPa(dictionary1.getDictionaryId());
            }
    }
    //删除字典
    public void deleteDic(String id) {

        selectAllSonPa(id);
        for(Dictionary dictionary:dictionaryDeList){
            dictionaryMapper.deleteByPrimaryKey(dictionary.getDictionaryId());
        }
        System.out.println(dictionaryDeList);
    }
    //通过字典的Code 获取字典名字
    public String getDicNameByCode(String code){
        DictionaryExample dictionaryExample=new DictionaryExample();
        DictionaryExample.Criteria criteria=dictionaryExample.createCriteria();
        criteria.andDicValueEqualTo(code);
        List<Dictionary> dicList=dictionaryMapper.selectByExample(dictionaryExample);
        String Name=null;
        for(Dictionary dictionary:dicList){
            Name=dictionary.getDicValue();
            break;
        }
        return Name;
    }
}
