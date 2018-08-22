package com.xh.comm.entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrgBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrgBeanExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdIsNull() {
            addCriterion("ORG_PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdIsNotNull() {
            addCriterion("ORG_PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdEqualTo(String value) {
            addCriterion("ORG_PARENT_ID =", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdNotEqualTo(String value) {
            addCriterion("ORG_PARENT_ID <>", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdGreaterThan(String value) {
            addCriterion("ORG_PARENT_ID >", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PARENT_ID >=", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdLessThan(String value) {
            addCriterion("ORG_PARENT_ID <", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_PARENT_ID <=", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdLike(String value) {
            addCriterion("ORG_PARENT_ID like", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdNotLike(String value) {
            addCriterion("ORG_PARENT_ID not like", value, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdIn(List<String> values) {
            addCriterion("ORG_PARENT_ID in", values, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdNotIn(List<String> values) {
            addCriterion("ORG_PARENT_ID not in", values, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdBetween(String value1, String value2) {
            addCriterion("ORG_PARENT_ID between", value1, value2, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgParentIdNotBetween(String value1, String value2) {
            addCriterion("ORG_PARENT_ID not between", value1, value2, "orgParentId");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgImgIsNull() {
            addCriterion("ORG_IMG is null");
            return (Criteria) this;
        }

        public Criteria andOrgImgIsNotNull() {
            addCriterion("ORG_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andOrgImgEqualTo(String value) {
            addCriterion("ORG_IMG =", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgNotEqualTo(String value) {
            addCriterion("ORG_IMG <>", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgGreaterThan(String value) {
            addCriterion("ORG_IMG >", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_IMG >=", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgLessThan(String value) {
            addCriterion("ORG_IMG <", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgLessThanOrEqualTo(String value) {
            addCriterion("ORG_IMG <=", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgLike(String value) {
            addCriterion("ORG_IMG like", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgNotLike(String value) {
            addCriterion("ORG_IMG not like", value, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgIn(List<String> values) {
            addCriterion("ORG_IMG in", values, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgNotIn(List<String> values) {
            addCriterion("ORG_IMG not in", values, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgBetween(String value1, String value2) {
            addCriterion("ORG_IMG between", value1, value2, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgImgNotBetween(String value1, String value2) {
            addCriterion("ORG_IMG not between", value1, value2, "orgImg");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteIsNull() {
            addCriterion("ORG_WEBSITE is null");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteIsNotNull() {
            addCriterion("ORG_WEBSITE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteEqualTo(String value) {
            addCriterion("ORG_WEBSITE =", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteNotEqualTo(String value) {
            addCriterion("ORG_WEBSITE <>", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteGreaterThan(String value) {
            addCriterion("ORG_WEBSITE >", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_WEBSITE >=", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteLessThan(String value) {
            addCriterion("ORG_WEBSITE <", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteLessThanOrEqualTo(String value) {
            addCriterion("ORG_WEBSITE <=", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteLike(String value) {
            addCriterion("ORG_WEBSITE like", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteNotLike(String value) {
            addCriterion("ORG_WEBSITE not like", value, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteIn(List<String> values) {
            addCriterion("ORG_WEBSITE in", values, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteNotIn(List<String> values) {
            addCriterion("ORG_WEBSITE not in", values, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteBetween(String value1, String value2) {
            addCriterion("ORG_WEBSITE between", value1, value2, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgWebsiteNotBetween(String value1, String value2) {
            addCriterion("ORG_WEBSITE not between", value1, value2, "orgWebsite");
            return (Criteria) this;
        }

        public Criteria andOrgEmailIsNull() {
            addCriterion("ORG_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andOrgEmailIsNotNull() {
            addCriterion("ORG_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andOrgEmailEqualTo(String value) {
            addCriterion("ORG_EMAIL =", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailNotEqualTo(String value) {
            addCriterion("ORG_EMAIL <>", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailGreaterThan(String value) {
            addCriterion("ORG_EMAIL >", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_EMAIL >=", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailLessThan(String value) {
            addCriterion("ORG_EMAIL <", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailLessThanOrEqualTo(String value) {
            addCriterion("ORG_EMAIL <=", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailLike(String value) {
            addCriterion("ORG_EMAIL like", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailNotLike(String value) {
            addCriterion("ORG_EMAIL not like", value, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailIn(List<String> values) {
            addCriterion("ORG_EMAIL in", values, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailNotIn(List<String> values) {
            addCriterion("ORG_EMAIL not in", values, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailBetween(String value1, String value2) {
            addCriterion("ORG_EMAIL between", value1, value2, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgEmailNotBetween(String value1, String value2) {
            addCriterion("ORG_EMAIL not between", value1, value2, "orgEmail");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIsNull() {
            addCriterion("ORG_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIsNotNull() {
            addCriterion("ORG_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneEqualTo(String value) {
            addCriterion("ORG_PHONE =", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotEqualTo(String value) {
            addCriterion("ORG_PHONE <>", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneGreaterThan(String value) {
            addCriterion("ORG_PHONE >", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PHONE >=", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLessThan(String value) {
            addCriterion("ORG_PHONE <", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLessThanOrEqualTo(String value) {
            addCriterion("ORG_PHONE <=", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLike(String value) {
            addCriterion("ORG_PHONE like", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotLike(String value) {
            addCriterion("ORG_PHONE not like", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIn(List<String> values) {
            addCriterion("ORG_PHONE in", values, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotIn(List<String> values) {
            addCriterion("ORG_PHONE not in", values, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneBetween(String value1, String value2) {
            addCriterion("ORG_PHONE between", value1, value2, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotBetween(String value1, String value2) {
            addCriterion("ORG_PHONE not between", value1, value2, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIsNull() {
            addCriterion("ORG_PROV_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIsNotNull() {
            addCriterion("ORG_PROV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdEqualTo(String value) {
            addCriterion("ORG_PROV_ID =", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotEqualTo(String value) {
            addCriterion("ORG_PROV_ID <>", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdGreaterThan(String value) {
            addCriterion("ORG_PROV_ID >", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_ID >=", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLessThan(String value) {
            addCriterion("ORG_PROV_ID <", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_ID <=", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLike(String value) {
            addCriterion("ORG_PROV_ID like", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotLike(String value) {
            addCriterion("ORG_PROV_ID not like", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIn(List<String> values) {
            addCriterion("ORG_PROV_ID in", values, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotIn(List<String> values) {
            addCriterion("ORG_PROV_ID not in", values, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdBetween(String value1, String value2) {
            addCriterion("ORG_PROV_ID between", value1, value2, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotBetween(String value1, String value2) {
            addCriterion("ORG_PROV_ID not between", value1, value2, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIsNull() {
            addCriterion("ORG_CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIsNotNull() {
            addCriterion("ORG_CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdEqualTo(String value) {
            addCriterion("ORG_CITY_ID =", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotEqualTo(String value) {
            addCriterion("ORG_CITY_ID <>", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThan(String value) {
            addCriterion("ORG_CITY_ID >", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID >=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThan(String value) {
            addCriterion("ORG_CITY_ID <", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID <=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLike(String value) {
            addCriterion("ORG_CITY_ID like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotLike(String value) {
            addCriterion("ORG_CITY_ID not like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIn(List<String> values) {
            addCriterion("ORG_CITY_ID in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotIn(List<String> values) {
            addCriterion("ORG_CITY_ID not in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID not between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdIsNull() {
            addCriterion("ORG_AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdIsNotNull() {
            addCriterion("ORG_AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdEqualTo(String value) {
            addCriterion("ORG_AREA_ID =", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotEqualTo(String value) {
            addCriterion("ORG_AREA_ID <>", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdGreaterThan(String value) {
            addCriterion("ORG_AREA_ID >", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AREA_ID >=", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdLessThan(String value) {
            addCriterion("ORG_AREA_ID <", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_AREA_ID <=", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdLike(String value) {
            addCriterion("ORG_AREA_ID like", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotLike(String value) {
            addCriterion("ORG_AREA_ID not like", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdIn(List<String> values) {
            addCriterion("ORG_AREA_ID in", values, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotIn(List<String> values) {
            addCriterion("ORG_AREA_ID not in", values, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdBetween(String value1, String value2) {
            addCriterion("ORG_AREA_ID between", value1, value2, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotBetween(String value1, String value2) {
            addCriterion("ORG_AREA_ID not between", value1, value2, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgDescIsNull() {
            addCriterion("ORG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andOrgDescIsNotNull() {
            addCriterion("ORG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andOrgDescEqualTo(String value) {
            addCriterion("ORG_DESC =", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotEqualTo(String value) {
            addCriterion("ORG_DESC <>", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescGreaterThan(String value) {
            addCriterion("ORG_DESC >", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_DESC >=", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescLessThan(String value) {
            addCriterion("ORG_DESC <", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescLessThanOrEqualTo(String value) {
            addCriterion("ORG_DESC <=", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescLike(String value) {
            addCriterion("ORG_DESC like", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotLike(String value) {
            addCriterion("ORG_DESC not like", value, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescIn(List<String> values) {
            addCriterion("ORG_DESC in", values, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotIn(List<String> values) {
            addCriterion("ORG_DESC not in", values, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescBetween(String value1, String value2) {
            addCriterion("ORG_DESC between", value1, value2, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andOrgDescNotBetween(String value1, String value2) {
            addCriterion("ORG_DESC not between", value1, value2, "orgDesc");
            return (Criteria) this;
        }

        public Criteria andSortKeyIsNull() {
            addCriterion("SORT_KEY is null");
            return (Criteria) this;
        }

        public Criteria andSortKeyIsNotNull() {
            addCriterion("SORT_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andSortKeyEqualTo(Integer value) {
            addCriterion("SORT_KEY =", value, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyNotEqualTo(Integer value) {
            addCriterion("SORT_KEY <>", value, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyGreaterThan(Integer value) {
            addCriterion("SORT_KEY >", value, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_KEY >=", value, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyLessThan(Integer value) {
            addCriterion("SORT_KEY <", value, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_KEY <=", value, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyIn(List<Integer> values) {
            addCriterion("SORT_KEY in", values, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyNotIn(List<Integer> values) {
            addCriterion("SORT_KEY not in", values, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyBetween(Integer value1, Integer value2) {
            addCriterion("SORT_KEY between", value1, value2, "sortKey");
            return (Criteria) this;
        }

        public Criteria andSortKeyNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_KEY not between", value1, value2, "sortKey");
            return (Criteria) this;
        }

        public Criteria andRecordStateIsNull() {
            addCriterion("RECORD_STATE is null");
            return (Criteria) this;
        }

        public Criteria andRecordStateIsNotNull() {
            addCriterion("RECORD_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andRecordStateEqualTo(String value) {
            addCriterion("RECORD_STATE =", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateNotEqualTo(String value) {
            addCriterion("RECORD_STATE <>", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateGreaterThan(String value) {
            addCriterion("RECORD_STATE >", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_STATE >=", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateLessThan(String value) {
            addCriterion("RECORD_STATE <", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateLessThanOrEqualTo(String value) {
            addCriterion("RECORD_STATE <=", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateLike(String value) {
            addCriterion("RECORD_STATE like", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateNotLike(String value) {
            addCriterion("RECORD_STATE not like", value, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateIn(List<String> values) {
            addCriterion("RECORD_STATE in", values, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateNotIn(List<String> values) {
            addCriterion("RECORD_STATE not in", values, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateBetween(String value1, String value2) {
            addCriterion("RECORD_STATE between", value1, value2, "recordState");
            return (Criteria) this;
        }

        public Criteria andRecordStateNotBetween(String value1, String value2) {
            addCriterion("RECORD_STATE not between", value1, value2, "recordState");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("updater is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("updater is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("updater =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("updater <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("updater >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("updater >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("updater <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("updater <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("updater like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("updater not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("updater in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("updater not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("updater between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("updater not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(String value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(String value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(String value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(String value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(String value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(String value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLike(String value) {
            addCriterion("is_delete like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotLike(String value) {
            addCriterion("is_delete not like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<String> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<String> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(String value1, String value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(String value1, String value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNull() {
            addCriterion("ORG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNotNull() {
            addCriterion("ORG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressEqualTo(String value) {
            addCriterion("ORG_ADDRESS =", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotEqualTo(String value) {
            addCriterion("ORG_ADDRESS <>", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThan(String value) {
            addCriterion("ORG_ADDRESS >", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS >=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThan(String value) {
            addCriterion("ORG_ADDRESS <", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS <=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLike(String value) {
            addCriterion("ORG_ADDRESS like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotLike(String value) {
            addCriterion("ORG_ADDRESS not like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIn(List<String> values) {
            addCriterion("ORG_ADDRESS in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotIn(List<String> values) {
            addCriterion("ORG_ADDRESS not in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS not between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNull() {
            addCriterion("MODIFY_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNotNull() {
            addCriterion("MODIFY_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdEqualTo(String value) {
            addCriterion("MODIFY_USER_ID =", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotEqualTo(String value) {
            addCriterion("MODIFY_USER_ID <>", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThan(String value) {
            addCriterion("MODIFY_USER_ID >", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFY_USER_ID >=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThan(String value) {
            addCriterion("MODIFY_USER_ID <", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThanOrEqualTo(String value) {
            addCriterion("MODIFY_USER_ID <=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLike(String value) {
            addCriterion("MODIFY_USER_ID like", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotLike(String value) {
            addCriterion("MODIFY_USER_ID not like", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIn(List<String> values) {
            addCriterion("MODIFY_USER_ID in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotIn(List<String> values) {
            addCriterion("MODIFY_USER_ID not in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdBetween(String value1, String value2) {
            addCriterion("MODIFY_USER_ID between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotBetween(String value1, String value2) {
            addCriterion("MODIFY_USER_ID not between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("MODIFY_TIME =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("MODIFY_TIME <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("MODIFY_TIME >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("MODIFY_TIME <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("MODIFY_TIME <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("MODIFY_TIME in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("MODIFY_TIME not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
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