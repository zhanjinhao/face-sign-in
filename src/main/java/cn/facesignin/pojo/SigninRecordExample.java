package cn.facesignin.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SigninRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SigninRecordExample() {
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

        public Criteria andAidIsNull() {
            addCriterion("aid is null");
            return (Criteria) this;
        }

        public Criteria andAidIsNotNull() {
            addCriterion("aid is not null");
            return (Criteria) this;
        }

        public Criteria andAidEqualTo(Integer value) {
            addCriterion("aid =", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotEqualTo(Integer value) {
            addCriterion("aid <>", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThan(Integer value) {
            addCriterion("aid >", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThanOrEqualTo(Integer value) {
            addCriterion("aid >=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThan(Integer value) {
            addCriterion("aid <", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThanOrEqualTo(Integer value) {
            addCriterion("aid <=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidIn(List<Integer> values) {
            addCriterion("aid in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotIn(List<Integer> values) {
            addCriterion("aid not in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidBetween(Integer value1, Integer value2) {
            addCriterion("aid between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotBetween(Integer value1, Integer value2) {
            addCriterion("aid not between", value1, value2, "aid");
            return (Criteria) this;
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

        public Criteria andSinTimeIsNull() {
            addCriterion("sin_time is null");
            return (Criteria) this;
        }

        public Criteria andSinTimeIsNotNull() {
            addCriterion("sin_time is not null");
            return (Criteria) this;
        }

        public Criteria andSinTimeEqualTo(Date value) {
            addCriterion("sin_time =", value, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeNotEqualTo(Date value) {
            addCriterion("sin_time <>", value, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeGreaterThan(Date value) {
            addCriterion("sin_time >", value, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sin_time >=", value, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeLessThan(Date value) {
            addCriterion("sin_time <", value, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeLessThanOrEqualTo(Date value) {
            addCriterion("sin_time <=", value, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeIn(List<Date> values) {
            addCriterion("sin_time in", values, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeNotIn(List<Date> values) {
            addCriterion("sin_time not in", values, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeBetween(Date value1, Date value2) {
            addCriterion("sin_time between", value1, value2, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSinTimeNotBetween(Date value1, Date value2) {
            addCriterion("sin_time not between", value1, value2, "sinTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeIsNull() {
            addCriterion("sout_time is null");
            return (Criteria) this;
        }

        public Criteria andSoutTimeIsNotNull() {
            addCriterion("sout_time is not null");
            return (Criteria) this;
        }

        public Criteria andSoutTimeEqualTo(Date value) {
            addCriterion("sout_time =", value, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeNotEqualTo(Date value) {
            addCriterion("sout_time <>", value, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeGreaterThan(Date value) {
            addCriterion("sout_time >", value, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sout_time >=", value, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeLessThan(Date value) {
            addCriterion("sout_time <", value, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeLessThanOrEqualTo(Date value) {
            addCriterion("sout_time <=", value, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeIn(List<Date> values) {
            addCriterion("sout_time in", values, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeNotIn(List<Date> values) {
            addCriterion("sout_time not in", values, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeBetween(Date value1, Date value2) {
            addCriterion("sout_time between", value1, value2, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSoutTimeNotBetween(Date value1, Date value2) {
            addCriterion("sout_time not between", value1, value2, "soutTime");
            return (Criteria) this;
        }

        public Criteria andSimgPathIsNull() {
            addCriterion("simg_path is null");
            return (Criteria) this;
        }

        public Criteria andSimgPathIsNotNull() {
            addCriterion("simg_path is not null");
            return (Criteria) this;
        }

        public Criteria andSimgPathEqualTo(String value) {
            addCriterion("simg_path =", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathNotEqualTo(String value) {
            addCriterion("simg_path <>", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathGreaterThan(String value) {
            addCriterion("simg_path >", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathGreaterThanOrEqualTo(String value) {
            addCriterion("simg_path >=", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathLessThan(String value) {
            addCriterion("simg_path <", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathLessThanOrEqualTo(String value) {
            addCriterion("simg_path <=", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathLike(String value) {
            addCriterion("simg_path like", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathNotLike(String value) {
            addCriterion("simg_path not like", value, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathIn(List<String> values) {
            addCriterion("simg_path in", values, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathNotIn(List<String> values) {
            addCriterion("simg_path not in", values, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathBetween(String value1, String value2) {
            addCriterion("simg_path between", value1, value2, "simgPath");
            return (Criteria) this;
        }

        public Criteria andSimgPathNotBetween(String value1, String value2) {
            addCriterion("simg_path not between", value1, value2, "simgPath");
            return (Criteria) this;
        }

        public Criteria andScheckTypeIsNull() {
            addCriterion("scheck_type is null");
            return (Criteria) this;
        }

        public Criteria andScheckTypeIsNotNull() {
            addCriterion("scheck_type is not null");
            return (Criteria) this;
        }

        public Criteria andScheckTypeEqualTo(String value) {
            addCriterion("scheck_type =", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeNotEqualTo(String value) {
            addCriterion("scheck_type <>", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeGreaterThan(String value) {
            addCriterion("scheck_type >", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeGreaterThanOrEqualTo(String value) {
            addCriterion("scheck_type >=", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeLessThan(String value) {
            addCriterion("scheck_type <", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeLessThanOrEqualTo(String value) {
            addCriterion("scheck_type <=", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeLike(String value) {
            addCriterion("scheck_type like", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeNotLike(String value) {
            addCriterion("scheck_type not like", value, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeIn(List<String> values) {
            addCriterion("scheck_type in", values, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeNotIn(List<String> values) {
            addCriterion("scheck_type not in", values, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeBetween(String value1, String value2) {
            addCriterion("scheck_type between", value1, value2, "scheckType");
            return (Criteria) this;
        }

        public Criteria andScheckTypeNotBetween(String value1, String value2) {
            addCriterion("scheck_type not between", value1, value2, "scheckType");
            return (Criteria) this;
        }

        public Criteria andSstatusIsNull() {
            addCriterion("sstatus is null");
            return (Criteria) this;
        }

        public Criteria andSstatusIsNotNull() {
            addCriterion("sstatus is not null");
            return (Criteria) this;
        }

        public Criteria andSstatusEqualTo(String value) {
            addCriterion("sstatus =", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusNotEqualTo(String value) {
            addCriterion("sstatus <>", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusGreaterThan(String value) {
            addCriterion("sstatus >", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusGreaterThanOrEqualTo(String value) {
            addCriterion("sstatus >=", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusLessThan(String value) {
            addCriterion("sstatus <", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusLessThanOrEqualTo(String value) {
            addCriterion("sstatus <=", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusLike(String value) {
            addCriterion("sstatus like", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusNotLike(String value) {
            addCriterion("sstatus not like", value, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusIn(List<String> values) {
            addCriterion("sstatus in", values, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusNotIn(List<String> values) {
            addCriterion("sstatus not in", values, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusBetween(String value1, String value2) {
            addCriterion("sstatus between", value1, value2, "sstatus");
            return (Criteria) this;
        }

        public Criteria andSstatusNotBetween(String value1, String value2) {
            addCriterion("sstatus not between", value1, value2, "sstatus");
            return (Criteria) this;
        }

        public Criteria andConfidenceIsNull() {
            addCriterion("confidence is null");
            return (Criteria) this;
        }

        public Criteria andConfidenceIsNotNull() {
            addCriterion("confidence is not null");
            return (Criteria) this;
        }

        public Criteria andConfidenceEqualTo(Double value) {
            addCriterion("confidence =", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotEqualTo(Double value) {
            addCriterion("confidence <>", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceGreaterThan(Double value) {
            addCriterion("confidence >", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceGreaterThanOrEqualTo(Double value) {
            addCriterion("confidence >=", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceLessThan(Double value) {
            addCriterion("confidence <", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceLessThanOrEqualTo(Double value) {
            addCriterion("confidence <=", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceIn(List<Double> values) {
            addCriterion("confidence in", values, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotIn(List<Double> values) {
            addCriterion("confidence not in", values, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceBetween(Double value1, Double value2) {
            addCriterion("confidence between", value1, value2, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotBetween(Double value1, Double value2) {
            addCriterion("confidence not between", value1, value2, "confidence");
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