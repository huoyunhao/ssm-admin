package cc.aies.web.beans;

import java.util.ArrayList;
import java.util.List;

public class PermUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PermUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserPermIdIsNull() {
            addCriterion("user_perm_id is null");
            return (Criteria) this;
        }

        public Criteria andUserPermIdIsNotNull() {
            addCriterion("user_perm_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserPermIdEqualTo(String value) {
            addCriterion("user_perm_id =", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdNotEqualTo(String value) {
            addCriterion("user_perm_id <>", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdGreaterThan(String value) {
            addCriterion("user_perm_id >", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_perm_id >=", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdLessThan(String value) {
            addCriterion("user_perm_id <", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdLessThanOrEqualTo(String value) {
            addCriterion("user_perm_id <=", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdLike(String value) {
            addCriterion("user_perm_id like", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdNotLike(String value) {
            addCriterion("user_perm_id not like", value, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdIn(List<String> values) {
            addCriterion("user_perm_id in", values, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdNotIn(List<String> values) {
            addCriterion("user_perm_id not in", values, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdBetween(String value1, String value2) {
            addCriterion("user_perm_id between", value1, value2, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserPermIdNotBetween(String value1, String value2) {
            addCriterion("user_perm_id not between", value1, value2, "userPermId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIsNull() {
            addCriterion("user_account is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountIsNotNull() {
            addCriterion("user_account is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountEqualTo(String value) {
            addCriterion("user_account =", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotEqualTo(String value) {
            addCriterion("user_account <>", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountGreaterThan(String value) {
            addCriterion("user_account >", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountGreaterThanOrEqualTo(String value) {
            addCriterion("user_account >=", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLessThan(String value) {
            addCriterion("user_account <", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLessThanOrEqualTo(String value) {
            addCriterion("user_account <=", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountLike(String value) {
            addCriterion("user_account like", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotLike(String value) {
            addCriterion("user_account not like", value, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountIn(List<String> values) {
            addCriterion("user_account in", values, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotIn(List<String> values) {
            addCriterion("user_account not in", values, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountBetween(String value1, String value2) {
            addCriterion("user_account between", value1, value2, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserAccountNotBetween(String value1, String value2) {
            addCriterion("user_account not between", value1, value2, "userAccount");
            return (Criteria) this;
        }

        public Criteria andUserPassIsNull() {
            addCriterion("user_pass is null");
            return (Criteria) this;
        }

        public Criteria andUserPassIsNotNull() {
            addCriterion("user_pass is not null");
            return (Criteria) this;
        }

        public Criteria andUserPassEqualTo(String value) {
            addCriterion("user_pass =", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotEqualTo(String value) {
            addCriterion("user_pass <>", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassGreaterThan(String value) {
            addCriterion("user_pass >", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassGreaterThanOrEqualTo(String value) {
            addCriterion("user_pass >=", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassLessThan(String value) {
            addCriterion("user_pass <", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassLessThanOrEqualTo(String value) {
            addCriterion("user_pass <=", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassLike(String value) {
            addCriterion("user_pass like", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotLike(String value) {
            addCriterion("user_pass not like", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassIn(List<String> values) {
            addCriterion("user_pass in", values, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotIn(List<String> values) {
            addCriterion("user_pass not in", values, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassBetween(String value1, String value2) {
            addCriterion("user_pass between", value1, value2, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotBetween(String value1, String value2) {
            addCriterion("user_pass not between", value1, value2, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNull() {
            addCriterion("user_gender is null");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNotNull() {
            addCriterion("user_gender is not null");
            return (Criteria) this;
        }

        public Criteria andUserGenderEqualTo(String value) {
            addCriterion("user_gender =", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotEqualTo(String value) {
            addCriterion("user_gender <>", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThan(String value) {
            addCriterion("user_gender >", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThanOrEqualTo(String value) {
            addCriterion("user_gender >=", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThan(String value) {
            addCriterion("user_gender <", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThanOrEqualTo(String value) {
            addCriterion("user_gender <=", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLike(String value) {
            addCriterion("user_gender like", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotLike(String value) {
            addCriterion("user_gender not like", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderIn(List<String> values) {
            addCriterion("user_gender in", values, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotIn(List<String> values) {
            addCriterion("user_gender not in", values, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderBetween(String value1, String value2) {
            addCriterion("user_gender between", value1, value2, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotBetween(String value1, String value2) {
            addCriterion("user_gender not between", value1, value2, "userGender");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andUserWorkidIsNull() {
            addCriterion("user_workid is null");
            return (Criteria) this;
        }

        public Criteria andUserWorkidIsNotNull() {
            addCriterion("user_workid is not null");
            return (Criteria) this;
        }

        public Criteria andUserWorkidEqualTo(String value) {
            addCriterion("user_workid =", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidNotEqualTo(String value) {
            addCriterion("user_workid <>", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidGreaterThan(String value) {
            addCriterion("user_workid >", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidGreaterThanOrEqualTo(String value) {
            addCriterion("user_workid >=", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidLessThan(String value) {
            addCriterion("user_workid <", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidLessThanOrEqualTo(String value) {
            addCriterion("user_workid <=", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidLike(String value) {
            addCriterion("user_workid like", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidNotLike(String value) {
            addCriterion("user_workid not like", value, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidIn(List<String> values) {
            addCriterion("user_workid in", values, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidNotIn(List<String> values) {
            addCriterion("user_workid not in", values, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidBetween(String value1, String value2) {
            addCriterion("user_workid between", value1, value2, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andUserWorkidNotBetween(String value1, String value2) {
            addCriterion("user_workid not between", value1, value2, "userWorkid");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIsTempIsNull() {
            addCriterion("is_temp is null");
            return (Criteria) this;
        }

        public Criteria andIsTempIsNotNull() {
            addCriterion("is_temp is not null");
            return (Criteria) this;
        }

        public Criteria andIsTempEqualTo(String value) {
            addCriterion("is_temp =", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempNotEqualTo(String value) {
            addCriterion("is_temp <>", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempGreaterThan(String value) {
            addCriterion("is_temp >", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempGreaterThanOrEqualTo(String value) {
            addCriterion("is_temp >=", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempLessThan(String value) {
            addCriterion("is_temp <", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempLessThanOrEqualTo(String value) {
            addCriterion("is_temp <=", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempLike(String value) {
            addCriterion("is_temp like", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempNotLike(String value) {
            addCriterion("is_temp not like", value, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempIn(List<String> values) {
            addCriterion("is_temp in", values, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempNotIn(List<String> values) {
            addCriterion("is_temp not in", values, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempBetween(String value1, String value2) {
            addCriterion("is_temp between", value1, value2, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsTempNotBetween(String value1, String value2) {
            addCriterion("is_temp not between", value1, value2, "isTemp");
            return (Criteria) this;
        }

        public Criteria andIsSubjectIsNull() {
            addCriterion("is_subject is null");
            return (Criteria) this;
        }

        public Criteria andIsSubjectIsNotNull() {
            addCriterion("is_subject is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubjectEqualTo(String value) {
            addCriterion("is_subject =", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectNotEqualTo(String value) {
            addCriterion("is_subject <>", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectGreaterThan(String value) {
            addCriterion("is_subject >", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("is_subject >=", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectLessThan(String value) {
            addCriterion("is_subject <", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectLessThanOrEqualTo(String value) {
            addCriterion("is_subject <=", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectLike(String value) {
            addCriterion("is_subject like", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectNotLike(String value) {
            addCriterion("is_subject not like", value, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectIn(List<String> values) {
            addCriterion("is_subject in", values, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectNotIn(List<String> values) {
            addCriterion("is_subject not in", values, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectBetween(String value1, String value2) {
            addCriterion("is_subject between", value1, value2, "isSubject");
            return (Criteria) this;
        }

        public Criteria andIsSubjectNotBetween(String value1, String value2) {
            addCriterion("is_subject not between", value1, value2, "isSubject");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}