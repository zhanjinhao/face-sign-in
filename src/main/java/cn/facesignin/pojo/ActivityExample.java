package cn.facesignin.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityExample() {
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

        public Criteria andAnameIsNull() {
            addCriterion("aname is null");
            return (Criteria) this;
        }

        public Criteria andAnameIsNotNull() {
            addCriterion("aname is not null");
            return (Criteria) this;
        }

        public Criteria andAnameEqualTo(String value) {
            addCriterion("aname =", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotEqualTo(String value) {
            addCriterion("aname <>", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThan(String value) {
            addCriterion("aname >", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThanOrEqualTo(String value) {
            addCriterion("aname >=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThan(String value) {
            addCriterion("aname <", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThanOrEqualTo(String value) {
            addCriterion("aname <=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLike(String value) {
            addCriterion("aname like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotLike(String value) {
            addCriterion("aname not like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameIn(List<String> values) {
            addCriterion("aname in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotIn(List<String> values) {
            addCriterion("aname not in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameBetween(String value1, String value2) {
            addCriterion("aname between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotBetween(String value1, String value2) {
            addCriterion("aname not between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andAstartTimeIsNull() {
            addCriterion("astart_time is null");
            return (Criteria) this;
        }

        public Criteria andAstartTimeIsNotNull() {
            addCriterion("astart_time is not null");
            return (Criteria) this;
        }

        public Criteria andAstartTimeEqualTo(Date value) {
            addCriterion("astart_time =", value, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeNotEqualTo(Date value) {
            addCriterion("astart_time <>", value, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeGreaterThan(Date value) {
            addCriterion("astart_time >", value, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("astart_time >=", value, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeLessThan(Date value) {
            addCriterion("astart_time <", value, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeLessThanOrEqualTo(Date value) {
            addCriterion("astart_time <=", value, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeIn(List<Date> values) {
            addCriterion("astart_time in", values, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeNotIn(List<Date> values) {
            addCriterion("astart_time not in", values, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeBetween(Date value1, Date value2) {
            addCriterion("astart_time between", value1, value2, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAstartTimeNotBetween(Date value1, Date value2) {
            addCriterion("astart_time not between", value1, value2, "astartTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeIsNull() {
            addCriterion("aend_time is null");
            return (Criteria) this;
        }

        public Criteria andAendTimeIsNotNull() {
            addCriterion("aend_time is not null");
            return (Criteria) this;
        }

        public Criteria andAendTimeEqualTo(Date value) {
            addCriterion("aend_time =", value, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeNotEqualTo(Date value) {
            addCriterion("aend_time <>", value, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeGreaterThan(Date value) {
            addCriterion("aend_time >", value, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("aend_time >=", value, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeLessThan(Date value) {
            addCriterion("aend_time <", value, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeLessThanOrEqualTo(Date value) {
            addCriterion("aend_time <=", value, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeIn(List<Date> values) {
            addCriterion("aend_time in", values, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeNotIn(List<Date> values) {
            addCriterion("aend_time not in", values, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeBetween(Date value1, Date value2) {
            addCriterion("aend_time between", value1, value2, "aendTime");
            return (Criteria) this;
        }

        public Criteria andAendTimeNotBetween(Date value1, Date value2) {
            addCriterion("aend_time not between", value1, value2, "aendTime");
            return (Criteria) this;
        }

        public Criteria andOidIsNull() {
            addCriterion("oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(Integer value) {
            addCriterion("oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(Integer value) {
            addCriterion("oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(Integer value) {
            addCriterion("oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(Integer value) {
            addCriterion("oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(Integer value) {
            addCriterion("oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(Integer value) {
            addCriterion("oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<Integer> values) {
            addCriterion("oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<Integer> values) {
            addCriterion("oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(Integer value1, Integer value2) {
            addCriterion("oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(Integer value1, Integer value2) {
            addCriterion("oid not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andGidIsNull() {
            addCriterion("gid is null");
            return (Criteria) this;
        }

        public Criteria andGidIsNotNull() {
            addCriterion("gid is not null");
            return (Criteria) this;
        }

        public Criteria andGidEqualTo(Integer value) {
            addCriterion("gid =", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotEqualTo(Integer value) {
            addCriterion("gid <>", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThan(Integer value) {
            addCriterion("gid >", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThanOrEqualTo(Integer value) {
            addCriterion("gid >=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThan(Integer value) {
            addCriterion("gid <", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThanOrEqualTo(Integer value) {
            addCriterion("gid <=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidIn(List<Integer> values) {
            addCriterion("gid in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotIn(List<Integer> values) {
            addCriterion("gid not in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidBetween(Integer value1, Integer value2) {
            addCriterion("gid between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotBetween(Integer value1, Integer value2) {
            addCriterion("gid not between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andAchargeManIsNull() {
            addCriterion("acharge_man is null");
            return (Criteria) this;
        }

        public Criteria andAchargeManIsNotNull() {
            addCriterion("acharge_man is not null");
            return (Criteria) this;
        }

        public Criteria andAchargeManEqualTo(String value) {
            addCriterion("acharge_man =", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManNotEqualTo(String value) {
            addCriterion("acharge_man <>", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManGreaterThan(String value) {
            addCriterion("acharge_man >", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManGreaterThanOrEqualTo(String value) {
            addCriterion("acharge_man >=", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManLessThan(String value) {
            addCriterion("acharge_man <", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManLessThanOrEqualTo(String value) {
            addCriterion("acharge_man <=", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManLike(String value) {
            addCriterion("acharge_man like", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManNotLike(String value) {
            addCriterion("acharge_man not like", value, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManIn(List<String> values) {
            addCriterion("acharge_man in", values, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManNotIn(List<String> values) {
            addCriterion("acharge_man not in", values, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManBetween(String value1, String value2) {
            addCriterion("acharge_man between", value1, value2, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAchargeManNotBetween(String value1, String value2) {
            addCriterion("acharge_man not between", value1, value2, "achargeMan");
            return (Criteria) this;
        }

        public Criteria andAcontactIsNull() {
            addCriterion("acontact is null");
            return (Criteria) this;
        }

        public Criteria andAcontactIsNotNull() {
            addCriterion("acontact is not null");
            return (Criteria) this;
        }

        public Criteria andAcontactEqualTo(String value) {
            addCriterion("acontact =", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactNotEqualTo(String value) {
            addCriterion("acontact <>", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactGreaterThan(String value) {
            addCriterion("acontact >", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactGreaterThanOrEqualTo(String value) {
            addCriterion("acontact >=", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactLessThan(String value) {
            addCriterion("acontact <", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactLessThanOrEqualTo(String value) {
            addCriterion("acontact <=", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactLike(String value) {
            addCriterion("acontact like", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactNotLike(String value) {
            addCriterion("acontact not like", value, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactIn(List<String> values) {
            addCriterion("acontact in", values, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactNotIn(List<String> values) {
            addCriterion("acontact not in", values, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactBetween(String value1, String value2) {
            addCriterion("acontact between", value1, value2, "acontact");
            return (Criteria) this;
        }

        public Criteria andAcontactNotBetween(String value1, String value2) {
            addCriterion("acontact not between", value1, value2, "acontact");
            return (Criteria) this;
        }

        public Criteria andAstatusIsNull() {
            addCriterion("astatus is null");
            return (Criteria) this;
        }

        public Criteria andAstatusIsNotNull() {
            addCriterion("astatus is not null");
            return (Criteria) this;
        }

        public Criteria andAstatusEqualTo(String value) {
            addCriterion("astatus =", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusNotEqualTo(String value) {
            addCriterion("astatus <>", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusGreaterThan(String value) {
            addCriterion("astatus >", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusGreaterThanOrEqualTo(String value) {
            addCriterion("astatus >=", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusLessThan(String value) {
            addCriterion("astatus <", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusLessThanOrEqualTo(String value) {
            addCriterion("astatus <=", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusLike(String value) {
            addCriterion("astatus like", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusNotLike(String value) {
            addCriterion("astatus not like", value, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusIn(List<String> values) {
            addCriterion("astatus in", values, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusNotIn(List<String> values) {
            addCriterion("astatus not in", values, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusBetween(String value1, String value2) {
            addCriterion("astatus between", value1, value2, "astatus");
            return (Criteria) this;
        }

        public Criteria andAstatusNotBetween(String value1, String value2) {
            addCriterion("astatus not between", value1, value2, "astatus");
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