package cn.facesignin.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUnameIsNull() {
            addCriterion("uname is null");
            return (Criteria) this;
        }

        public Criteria andUnameIsNotNull() {
            addCriterion("uname is not null");
            return (Criteria) this;
        }

        public Criteria andUnameEqualTo(String value) {
            addCriterion("uname =", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotEqualTo(String value) {
            addCriterion("uname <>", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThan(String value) {
            addCriterion("uname >", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThanOrEqualTo(String value) {
            addCriterion("uname >=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThan(String value) {
            addCriterion("uname <", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThanOrEqualTo(String value) {
            addCriterion("uname <=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLike(String value) {
            addCriterion("uname like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotLike(String value) {
            addCriterion("uname not like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameIn(List<String> values) {
            addCriterion("uname in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotIn(List<String> values) {
            addCriterion("uname not in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameBetween(String value1, String value2) {
            addCriterion("uname between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotBetween(String value1, String value2) {
            addCriterion("uname not between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andUpwdIsNull() {
            addCriterion("upwd is null");
            return (Criteria) this;
        }

        public Criteria andUpwdIsNotNull() {
            addCriterion("upwd is not null");
            return (Criteria) this;
        }

        public Criteria andUpwdEqualTo(String value) {
            addCriterion("upwd =", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotEqualTo(String value) {
            addCriterion("upwd <>", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdGreaterThan(String value) {
            addCriterion("upwd >", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdGreaterThanOrEqualTo(String value) {
            addCriterion("upwd >=", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdLessThan(String value) {
            addCriterion("upwd <", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdLessThanOrEqualTo(String value) {
            addCriterion("upwd <=", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdLike(String value) {
            addCriterion("upwd like", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotLike(String value) {
            addCriterion("upwd not like", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdIn(List<String> values) {
            addCriterion("upwd in", values, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotIn(List<String> values) {
            addCriterion("upwd not in", values, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdBetween(String value1, String value2) {
            addCriterion("upwd between", value1, value2, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotBetween(String value1, String value2) {
            addCriterion("upwd not between", value1, value2, "upwd");
            return (Criteria) this;
        }

        public Criteria andUemailIsNull() {
            addCriterion("uemail is null");
            return (Criteria) this;
        }

        public Criteria andUemailIsNotNull() {
            addCriterion("uemail is not null");
            return (Criteria) this;
        }

        public Criteria andUemailEqualTo(String value) {
            addCriterion("uemail =", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotEqualTo(String value) {
            addCriterion("uemail <>", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailGreaterThan(String value) {
            addCriterion("uemail >", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailGreaterThanOrEqualTo(String value) {
            addCriterion("uemail >=", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailLessThan(String value) {
            addCriterion("uemail <", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailLessThanOrEqualTo(String value) {
            addCriterion("uemail <=", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailLike(String value) {
            addCriterion("uemail like", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotLike(String value) {
            addCriterion("uemail not like", value, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailIn(List<String> values) {
            addCriterion("uemail in", values, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotIn(List<String> values) {
            addCriterion("uemail not in", values, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailBetween(String value1, String value2) {
            addCriterion("uemail between", value1, value2, "uemail");
            return (Criteria) this;
        }

        public Criteria andUemailNotBetween(String value1, String value2) {
            addCriterion("uemail not between", value1, value2, "uemail");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenIsNull() {
            addCriterion("uface_token is null");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenIsNotNull() {
            addCriterion("uface_token is not null");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenEqualTo(String value) {
            addCriterion("uface_token =", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenNotEqualTo(String value) {
            addCriterion("uface_token <>", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenGreaterThan(String value) {
            addCriterion("uface_token >", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenGreaterThanOrEqualTo(String value) {
            addCriterion("uface_token >=", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenLessThan(String value) {
            addCriterion("uface_token <", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenLessThanOrEqualTo(String value) {
            addCriterion("uface_token <=", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenLike(String value) {
            addCriterion("uface_token like", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenNotLike(String value) {
            addCriterion("uface_token not like", value, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenIn(List<String> values) {
            addCriterion("uface_token in", values, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenNotIn(List<String> values) {
            addCriterion("uface_token not in", values, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenBetween(String value1, String value2) {
            addCriterion("uface_token between", value1, value2, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUfaceTokenNotBetween(String value1, String value2) {
            addCriterion("uface_token not between", value1, value2, "ufaceToken");
            return (Criteria) this;
        }

        public Criteria andUimgPathIsNull() {
            addCriterion("uimg_path is null");
            return (Criteria) this;
        }

        public Criteria andUimgPathIsNotNull() {
            addCriterion("uimg_path is not null");
            return (Criteria) this;
        }

        public Criteria andUimgPathEqualTo(String value) {
            addCriterion("uimg_path =", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathNotEqualTo(String value) {
            addCriterion("uimg_path <>", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathGreaterThan(String value) {
            addCriterion("uimg_path >", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathGreaterThanOrEqualTo(String value) {
            addCriterion("uimg_path >=", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathLessThan(String value) {
            addCriterion("uimg_path <", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathLessThanOrEqualTo(String value) {
            addCriterion("uimg_path <=", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathLike(String value) {
            addCriterion("uimg_path like", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathNotLike(String value) {
            addCriterion("uimg_path not like", value, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathIn(List<String> values) {
            addCriterion("uimg_path in", values, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathNotIn(List<String> values) {
            addCriterion("uimg_path not in", values, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathBetween(String value1, String value2) {
            addCriterion("uimg_path between", value1, value2, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUimgPathNotBetween(String value1, String value2) {
            addCriterion("uimg_path not between", value1, value2, "uimgPath");
            return (Criteria) this;
        }

        public Criteria andUstatusIsNull() {
            addCriterion("ustatus is null");
            return (Criteria) this;
        }

        public Criteria andUstatusIsNotNull() {
            addCriterion("ustatus is not null");
            return (Criteria) this;
        }

        public Criteria andUstatusEqualTo(String value) {
            addCriterion("ustatus =", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusNotEqualTo(String value) {
            addCriterion("ustatus <>", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusGreaterThan(String value) {
            addCriterion("ustatus >", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusGreaterThanOrEqualTo(String value) {
            addCriterion("ustatus >=", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusLessThan(String value) {
            addCriterion("ustatus <", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusLessThanOrEqualTo(String value) {
            addCriterion("ustatus <=", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusLike(String value) {
            addCriterion("ustatus like", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusNotLike(String value) {
            addCriterion("ustatus not like", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusIn(List<String> values) {
            addCriterion("ustatus in", values, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusNotIn(List<String> values) {
            addCriterion("ustatus not in", values, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusBetween(String value1, String value2) {
            addCriterion("ustatus between", value1, value2, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusNotBetween(String value1, String value2) {
            addCriterion("ustatus not between", value1, value2, "ustatus");
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