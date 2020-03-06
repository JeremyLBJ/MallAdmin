package com.lhd.sys.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassificationofgoodsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public ClassificationofgoodsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCtypenameIsNull() {
            addCriterion("cTypeName is null");
            return (Criteria) this;
        }

        public Criteria andCtypenameIsNotNull() {
            addCriterion("cTypeName is not null");
            return (Criteria) this;
        }

        public Criteria andCtypenameEqualTo(String value) {
            addCriterion("cTypeName =", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameNotEqualTo(String value) {
            addCriterion("cTypeName <>", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameGreaterThan(String value) {
            addCriterion("cTypeName >", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameGreaterThanOrEqualTo(String value) {
            addCriterion("cTypeName >=", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameLessThan(String value) {
            addCriterion("cTypeName <", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameLessThanOrEqualTo(String value) {
            addCriterion("cTypeName <=", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameLike(String value) {
            addCriterion("cTypeName like", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameNotLike(String value) {
            addCriterion("cTypeName not like", value, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameIn(List<String> values) {
            addCriterion("cTypeName in", values, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameNotIn(List<String> values) {
            addCriterion("cTypeName not in", values, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameBetween(String value1, String value2) {
            addCriterion("cTypeName between", value1, value2, "ctypename");
            return (Criteria) this;
        }

        public Criteria andCtypenameNotBetween(String value1, String value2) {
            addCriterion("cTypeName not between", value1, value2, "ctypename");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table classificationofgoods
     *
     * @mbg.generated do_not_delete_during_merge Sat Nov 16 18:45:00 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table classificationofgoods
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
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