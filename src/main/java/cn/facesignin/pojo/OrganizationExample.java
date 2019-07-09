package cn.facesignin.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrganizationExample() {
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

        public Criteria andOpwdIsNull() {
            addCriterion("opwd is null");
            return (Criteria) this;
        }

        public Criteria andOpwdIsNotNull() {
            addCriterion("opwd is not null");
            return (Criteria) this;
        }

        public Criteria andOpwdEqualTo(String value) {
            addCriterion("opwd =", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdNotEqualTo(String value) {
            addCriterion("opwd <>", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdGreaterThan(String value) {
            addCriterion("opwd >", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdGreaterThanOrEqualTo(String value) {
            addCriterion("opwd >=", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdLessThan(String value) {
            addCriterion("opwd <", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdLessThanOrEqualTo(String value) {
            addCriterion("opwd <=", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdLike(String value) {
            addCriterion("opwd like", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdNotLike(String value) {
            addCriterion("opwd not like", value, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdIn(List<String> values) {
            addCriterion("opwd in", values, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdNotIn(List<String> values) {
            addCriterion("opwd not in", values, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdBetween(String value1, String value2) {
            addCriterion("opwd between", value1, value2, "opwd");
            return (Criteria) this;
        }

        public Criteria andOpwdNotBetween(String value1, String value2) {
            addCriterion("opwd not between", value1, value2, "opwd");
            return (Criteria) this;
        }

        public Criteria andOemailIsNull() {
            addCriterion("oemail is null");
            return (Criteria) this;
        }

        public Criteria andOemailIsNotNull() {
            addCriterion("oemail is not null");
            return (Criteria) this;
        }

        public Criteria andOemailEqualTo(String value) {
            addCriterion("oemail =", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailNotEqualTo(String value) {
            addCriterion("oemail <>", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailGreaterThan(String value) {
            addCriterion("oemail >", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailGreaterThanOrEqualTo(String value) {
            addCriterion("oemail >=", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailLessThan(String value) {
            addCriterion("oemail <", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailLessThanOrEqualTo(String value) {
            addCriterion("oemail <=", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailLike(String value) {
            addCriterion("oemail like", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailNotLike(String value) {
            addCriterion("oemail not like", value, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailIn(List<String> values) {
            addCriterion("oemail in", values, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailNotIn(List<String> values) {
            addCriterion("oemail not in", values, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailBetween(String value1, String value2) {
            addCriterion("oemail between", value1, value2, "oemail");
            return (Criteria) this;
        }

        public Criteria andOemailNotBetween(String value1, String value2) {
            addCriterion("oemail not between", value1, value2, "oemail");
            return (Criteria) this;
        }

        public Criteria andOnameIsNull() {
            addCriterion("oname is null");
            return (Criteria) this;
        }

        public Criteria andOnameIsNotNull() {
            addCriterion("oname is not null");
            return (Criteria) this;
        }

        public Criteria andOnameEqualTo(String value) {
            addCriterion("oname =", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameNotEqualTo(String value) {
            addCriterion("oname <>", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameGreaterThan(String value) {
            addCriterion("oname >", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameGreaterThanOrEqualTo(String value) {
            addCriterion("oname >=", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameLessThan(String value) {
            addCriterion("oname <", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameLessThanOrEqualTo(String value) {
            addCriterion("oname <=", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameLike(String value) {
            addCriterion("oname like", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameNotLike(String value) {
            addCriterion("oname not like", value, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameIn(List<String> values) {
            addCriterion("oname in", values, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameNotIn(List<String> values) {
            addCriterion("oname not in", values, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameBetween(String value1, String value2) {
            addCriterion("oname between", value1, value2, "oname");
            return (Criteria) this;
        }

        public Criteria andOnameNotBetween(String value1, String value2) {
            addCriterion("oname not between", value1, value2, "oname");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeIsNull() {
            addCriterion("ocreate_time is null");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeIsNotNull() {
            addCriterion("ocreate_time is not null");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeEqualTo(Date value) {
            addCriterion("ocreate_time =", value, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeNotEqualTo(Date value) {
            addCriterion("ocreate_time <>", value, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeGreaterThan(Date value) {
            addCriterion("ocreate_time >", value, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ocreate_time >=", value, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeLessThan(Date value) {
            addCriterion("ocreate_time <", value, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("ocreate_time <=", value, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeIn(List<Date> values) {
            addCriterion("ocreate_time in", values, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeNotIn(List<Date> values) {
            addCriterion("ocreate_time not in", values, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeBetween(Date value1, Date value2) {
            addCriterion("ocreate_time between", value1, value2, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOcreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("ocreate_time not between", value1, value2, "ocreateTime");
            return (Criteria) this;
        }

        public Criteria andOownerIsNull() {
            addCriterion("oowner is null");
            return (Criteria) this;
        }

        public Criteria andOownerIsNotNull() {
            addCriterion("oowner is not null");
            return (Criteria) this;
        }

        public Criteria andOownerEqualTo(String value) {
            addCriterion("oowner =", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerNotEqualTo(String value) {
            addCriterion("oowner <>", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerGreaterThan(String value) {
            addCriterion("oowner >", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerGreaterThanOrEqualTo(String value) {
            addCriterion("oowner >=", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerLessThan(String value) {
            addCriterion("oowner <", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerLessThanOrEqualTo(String value) {
            addCriterion("oowner <=", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerLike(String value) {
            addCriterion("oowner like", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerNotLike(String value) {
            addCriterion("oowner not like", value, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerIn(List<String> values) {
            addCriterion("oowner in", values, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerNotIn(List<String> values) {
            addCriterion("oowner not in", values, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerBetween(String value1, String value2) {
            addCriterion("oowner between", value1, value2, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerNotBetween(String value1, String value2) {
            addCriterion("oowner not between", value1, value2, "oowner");
            return (Criteria) this;
        }

        public Criteria andOownerEmailIsNull() {
            addCriterion("oowner_email is null");
            return (Criteria) this;
        }

        public Criteria andOownerEmailIsNotNull() {
            addCriterion("oowner_email is not null");
            return (Criteria) this;
        }

        public Criteria andOownerEmailEqualTo(String value) {
            addCriterion("oowner_email =", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailNotEqualTo(String value) {
            addCriterion("oowner_email <>", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailGreaterThan(String value) {
            addCriterion("oowner_email >", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("oowner_email >=", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailLessThan(String value) {
            addCriterion("oowner_email <", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailLessThanOrEqualTo(String value) {
            addCriterion("oowner_email <=", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailLike(String value) {
            addCriterion("oowner_email like", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailNotLike(String value) {
            addCriterion("oowner_email not like", value, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailIn(List<String> values) {
            addCriterion("oowner_email in", values, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailNotIn(List<String> values) {
            addCriterion("oowner_email not in", values, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailBetween(String value1, String value2) {
            addCriterion("oowner_email between", value1, value2, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOownerEmailNotBetween(String value1, String value2) {
            addCriterion("oowner_email not between", value1, value2, "oownerEmail");
            return (Criteria) this;
        }

        public Criteria andOstatusIsNull() {
            addCriterion("ostatus is null");
            return (Criteria) this;
        }

        public Criteria andOstatusIsNotNull() {
            addCriterion("ostatus is not null");
            return (Criteria) this;
        }

        public Criteria andOstatusEqualTo(String value) {
            addCriterion("ostatus =", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusNotEqualTo(String value) {
            addCriterion("ostatus <>", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusGreaterThan(String value) {
            addCriterion("ostatus >", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusGreaterThanOrEqualTo(String value) {
            addCriterion("ostatus >=", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusLessThan(String value) {
            addCriterion("ostatus <", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusLessThanOrEqualTo(String value) {
            addCriterion("ostatus <=", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusLike(String value) {
            addCriterion("ostatus like", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusNotLike(String value) {
            addCriterion("ostatus not like", value, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusIn(List<String> values) {
            addCriterion("ostatus in", values, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusNotIn(List<String> values) {
            addCriterion("ostatus not in", values, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusBetween(String value1, String value2) {
            addCriterion("ostatus between", value1, value2, "ostatus");
            return (Criteria) this;
        }

        public Criteria andOstatusNotBetween(String value1, String value2) {
            addCriterion("ostatus not between", value1, value2, "ostatus");
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