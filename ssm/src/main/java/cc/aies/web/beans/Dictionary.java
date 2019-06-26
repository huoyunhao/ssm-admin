package cc.aies.web.beans;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private String dictionaryId;

    private String dicValue;

    private String parentId;

    private String remark;

    private String dicNum;

    public List<Dictionary> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Dictionary> childrenList) {
        this.childrenList = childrenList;
    }

    private List<Dictionary> childrenList=new ArrayList<>();

    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId == null ? null : dictionaryId.trim();
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDicNum() {
        return dicNum;
    }

    public void setDicNum(String dicNum) {
        this.dicNum = dicNum == null ? null : dicNum.trim();
    }
}