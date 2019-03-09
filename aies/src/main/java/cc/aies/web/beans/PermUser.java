package cc.aies.web.beans;

public class PermUser {
    private String userPermId;

    private String userAccount;

    private String userPass;

    private String userName;

    private String userGender;

    private String remark;

    private String userWorkid;

    private String companyId;

    private String status;

    private String isTemp;

    private String isSubject;




    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String companyName;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    private String roleId;

    public String getCuttingName() {
        return cuttingName;
    }

    public void setCuttingName(String cuttingName) {
        this.cuttingName = cuttingName;
    }

    //题号名
    private String cuttingName;

    public String getUserPermId() {
        return userPermId;
    }

    public void setUserPermId(String userPermId) {
        this.userPermId = userPermId == null ? null : userPermId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender == null ? null : userGender.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserWorkid() {
        return userWorkid;
    }

    public void setUserWorkid(String userWorkid) {
        this.userWorkid = userWorkid == null ? null : userWorkid.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(String isTemp) {
        this.isTemp = isTemp == null ? null : isTemp.trim();
    }

    public String getIsSubject() {
        return isSubject;
    }

    public void setIsSubject(String isSubject) {
        this.isSubject = isSubject == null ? null : isSubject.trim();
    }
}