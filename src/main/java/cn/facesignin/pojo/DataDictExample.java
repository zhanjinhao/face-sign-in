package cn.facesignin.pojo;

import java.util.ArrayList;
import java.util.List;

public class DataDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataDictExample() {
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

        public Criteria andDtypeIsNull() {
            addCriterion("dtype is null");
            return (Criteria) this;
        }

        public Criteria andDtypeIsNotNull() {
            addCriterion("dtype is not null");
            return (Criteria) this;
        }

        public Criteria andDtypeEqualTo(String value) {
            addCriterion("dtype =", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotEqualTo(String value) {
            addCriterion("dtype <>", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeGreaterThan(String value) {
            addCriterion("dtype >", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeGreaterThanOrEqualTo(String value) {
            addCriterion("dtype >=", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeLessThan(String value) {
            addCriterion("dtype <", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeLessThanOrEqualTo(String value) {
            addCriterion("dtype <=", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeLike(String value) {
            addCriterion("dtype like", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotLike(String value) {
            addCriterion("dtype not like", value, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeIn(List<String> values) {
            addCriterion("dtype in", values, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotIn(List<String> values) {
            addCriterion("dtype not in", values, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeBetween(String value1, String value2) {
            addCriterion("dtype between", value1, value2, "dtype");
            return (Criteria) this;
        }

        public Criteria andDtypeNotBetween(String value1, String value2) {
            addCriterion("dtype not between", value1, value2, "dtype");
            return (Criteria) this;
        }

        public Criteria andDnameIsNull() {
            addCriterion("dname is null");
            return (Criteria) this;
        }

        public Criteria andDnameIsNotNull() {
            addCriterion("dname is not null");
            return (Criteria) this;
        }

        public Criteria andDnameEqualTo(String value) {
            addCriterion("dname =", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotEqualTo(String value) {
            addCriterion("dname <>", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameGreaterThan(String value) {
            addCriterion("dname >", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameGreaterThanOrEqualTo(String value) {
            addCriterion("dname >=", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLessThan(String value) {
            addCriterion("dname <", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLessThanOrEqualTo(String value) {
            addCriterion("dname <=", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameLike(String value) {
            addCriterion("dname like", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotLike(String value) {
            addCriterion("dname not like", value, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameIn(List<String> values) {
            addCriterion("dname in", values, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotIn(List<String> values) {
            addCriterion("dname not in", values, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameBetween(String value1, String value2) {
            addCriterion("dname between", value1, value2, "dname");
            return (Criteria) this;
        }

        public Criteria andDnameNotBetween(String value1, String value2) {
            addCriterion("dname not between", value1, value2, "dname");
            return (Criteria) this;
        }

        public Criteria andDdataIsNull() {
            addCriterion("ddata is null");
            return (Criteria) this;
        }

        public Criteria andDdataIsNotNull() {
            addCriterion("ddata is not null");
            return (Criteria) this;
        }

        public Criteria andDdataEqualTo(String value) {
            addCriterion("ddata =", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataNotEqualTo(String value) {
            addCriterion("ddata <>", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataGreaterThan(String value) {
            addCriterion("ddata >", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataGreaterThanOrEqualTo(String value) {
            addCriterion("ddata >=", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataLessThan(String value) {
            addCriterion("ddata <", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataLessThanOrEqualTo(String value) {
            addCriterion("ddata <=", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataLike(String value) {
            addCriterion("ddata like", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataNotLike(String value) {
            addCriterion("ddata not like", value, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataIn(List<String> values) {
            addCriterion("ddata in", values, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataNotIn(List<String> values) {
            addCriterion("ddata not in", values, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataBetween(String value1, String value2) {
            addCriterion("ddata between", value1, value2, "ddata");
            return (Criteria) this;
        }

        public Criteria andDdataNotBetween(String value1, String value2) {
            addCriterion("ddata not between", value1, value2, "ddata");
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