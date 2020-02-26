package mobi.huanyuan.spider.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpiderHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpiderHistoryExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMaxDepthIsNull() {
            addCriterion("MAX_DEPTH is null");
            return (Criteria) this;
        }

        public Criteria andMaxDepthIsNotNull() {
            addCriterion("MAX_DEPTH is not null");
            return (Criteria) this;
        }

        public Criteria andMaxDepthEqualTo(Integer value) {
            addCriterion("MAX_DEPTH =", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthNotEqualTo(Integer value) {
            addCriterion("MAX_DEPTH <>", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthGreaterThan(Integer value) {
            addCriterion("MAX_DEPTH >", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthGreaterThanOrEqualTo(Integer value) {
            addCriterion("MAX_DEPTH >=", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthLessThan(Integer value) {
            addCriterion("MAX_DEPTH <", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthLessThanOrEqualTo(Integer value) {
            addCriterion("MAX_DEPTH <=", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthIn(List<Integer> values) {
            addCriterion("MAX_DEPTH in", values, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthNotIn(List<Integer> values) {
            addCriterion("MAX_DEPTH not in", values, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthBetween(Integer value1, Integer value2) {
            addCriterion("MAX_DEPTH between", value1, value2, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthNotBetween(Integer value1, Integer value2) {
            addCriterion("MAX_DEPTH not between", value1, value2, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumIsNull() {
            addCriterion("HTML_THREAD_NUM is null");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumIsNotNull() {
            addCriterion("HTML_THREAD_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumEqualTo(Integer value) {
            addCriterion("HTML_THREAD_NUM =", value, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumNotEqualTo(Integer value) {
            addCriterion("HTML_THREAD_NUM <>", value, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumGreaterThan(Integer value) {
            addCriterion("HTML_THREAD_NUM >", value, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("HTML_THREAD_NUM >=", value, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumLessThan(Integer value) {
            addCriterion("HTML_THREAD_NUM <", value, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumLessThanOrEqualTo(Integer value) {
            addCriterion("HTML_THREAD_NUM <=", value, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumIn(List<Integer> values) {
            addCriterion("HTML_THREAD_NUM in", values, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumNotIn(List<Integer> values) {
            addCriterion("HTML_THREAD_NUM not in", values, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumBetween(Integer value1, Integer value2) {
            addCriterion("HTML_THREAD_NUM between", value1, value2, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andHtmlThreadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("HTML_THREAD_NUM not between", value1, value2, "htmlThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumIsNull() {
            addCriterion("PARSE_THREAD_NUM is null");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumIsNotNull() {
            addCriterion("PARSE_THREAD_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumEqualTo(Integer value) {
            addCriterion("PARSE_THREAD_NUM =", value, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumNotEqualTo(Integer value) {
            addCriterion("PARSE_THREAD_NUM <>", value, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumGreaterThan(Integer value) {
            addCriterion("PARSE_THREAD_NUM >", value, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARSE_THREAD_NUM >=", value, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumLessThan(Integer value) {
            addCriterion("PARSE_THREAD_NUM <", value, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumLessThanOrEqualTo(Integer value) {
            addCriterion("PARSE_THREAD_NUM <=", value, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumIn(List<Integer> values) {
            addCriterion("PARSE_THREAD_NUM in", values, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumNotIn(List<Integer> values) {
            addCriterion("PARSE_THREAD_NUM not in", values, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumBetween(Integer value1, Integer value2) {
            addCriterion("PARSE_THREAD_NUM between", value1, value2, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andParseThreadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("PARSE_THREAD_NUM not between", value1, value2, "parseThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumIsNull() {
            addCriterion("STORE_THREAD_NUM is null");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumIsNotNull() {
            addCriterion("STORE_THREAD_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumEqualTo(Integer value) {
            addCriterion("STORE_THREAD_NUM =", value, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumNotEqualTo(Integer value) {
            addCriterion("STORE_THREAD_NUM <>", value, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumGreaterThan(Integer value) {
            addCriterion("STORE_THREAD_NUM >", value, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("STORE_THREAD_NUM >=", value, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumLessThan(Integer value) {
            addCriterion("STORE_THREAD_NUM <", value, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumLessThanOrEqualTo(Integer value) {
            addCriterion("STORE_THREAD_NUM <=", value, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumIn(List<Integer> values) {
            addCriterion("STORE_THREAD_NUM in", values, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumNotIn(List<Integer> values) {
            addCriterion("STORE_THREAD_NUM not in", values, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumBetween(Integer value1, Integer value2) {
            addCriterion("STORE_THREAD_NUM between", value1, value2, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andStoreThreadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("STORE_THREAD_NUM not between", value1, value2, "storeThreadNum");
            return (Criteria) this;
        }

        public Criteria andKeyWordsIsNull() {
            addCriterion("KEY_WORDS is null");
            return (Criteria) this;
        }

        public Criteria andKeyWordsIsNotNull() {
            addCriterion("KEY_WORDS is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWordsEqualTo(String value) {
            addCriterion("KEY_WORDS =", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsNotEqualTo(String value) {
            addCriterion("KEY_WORDS <>", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsGreaterThan(String value) {
            addCriterion("KEY_WORDS >", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_WORDS >=", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsLessThan(String value) {
            addCriterion("KEY_WORDS <", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsLessThanOrEqualTo(String value) {
            addCriterion("KEY_WORDS <=", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsLike(String value) {
            addCriterion("KEY_WORDS like", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsNotLike(String value) {
            addCriterion("KEY_WORDS not like", value, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsIn(List<String> values) {
            addCriterion("KEY_WORDS in", values, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsNotIn(List<String> values) {
            addCriterion("KEY_WORDS not in", values, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsBetween(String value1, String value2) {
            addCriterion("KEY_WORDS between", value1, value2, "keyWords");
            return (Criteria) this;
        }

        public Criteria andKeyWordsNotBetween(String value1, String value2) {
            addCriterion("KEY_WORDS not between", value1, value2, "keyWords");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIsNull() {
            addCriterion("STORE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIsNotNull() {
            addCriterion("STORE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStoreTypeEqualTo(String value) {
            addCriterion("STORE_TYPE =", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotEqualTo(String value) {
            addCriterion("STORE_TYPE <>", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeGreaterThan(String value) {
            addCriterion("STORE_TYPE >", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STORE_TYPE >=", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLessThan(String value) {
            addCriterion("STORE_TYPE <", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLessThanOrEqualTo(String value) {
            addCriterion("STORE_TYPE <=", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeLike(String value) {
            addCriterion("STORE_TYPE like", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotLike(String value) {
            addCriterion("STORE_TYPE not like", value, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeIn(List<String> values) {
            addCriterion("STORE_TYPE in", values, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotIn(List<String> values) {
            addCriterion("STORE_TYPE not in", values, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeBetween(String value1, String value2) {
            addCriterion("STORE_TYPE between", value1, value2, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreTypeNotBetween(String value1, String value2) {
            addCriterion("STORE_TYPE not between", value1, value2, "storeType");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathIsNull() {
            addCriterion("STORE_LOCAL_PATH is null");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathIsNotNull() {
            addCriterion("STORE_LOCAL_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathEqualTo(String value) {
            addCriterion("STORE_LOCAL_PATH =", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathNotEqualTo(String value) {
            addCriterion("STORE_LOCAL_PATH <>", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathGreaterThan(String value) {
            addCriterion("STORE_LOCAL_PATH >", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathGreaterThanOrEqualTo(String value) {
            addCriterion("STORE_LOCAL_PATH >=", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathLessThan(String value) {
            addCriterion("STORE_LOCAL_PATH <", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathLessThanOrEqualTo(String value) {
            addCriterion("STORE_LOCAL_PATH <=", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathLike(String value) {
            addCriterion("STORE_LOCAL_PATH like", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathNotLike(String value) {
            addCriterion("STORE_LOCAL_PATH not like", value, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathIn(List<String> values) {
            addCriterion("STORE_LOCAL_PATH in", values, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathNotIn(List<String> values) {
            addCriterion("STORE_LOCAL_PATH not in", values, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathBetween(String value1, String value2) {
            addCriterion("STORE_LOCAL_PATH between", value1, value2, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andStoreLocalPathNotBetween(String value1, String value2) {
            addCriterion("STORE_LOCAL_PATH not between", value1, value2, "storeLocalPath");
            return (Criteria) this;
        }

        public Criteria andDayIsNull() {
            addCriterion("DAY is null");
            return (Criteria) this;
        }

        public Criteria andDayIsNotNull() {
            addCriterion("DAY is not null");
            return (Criteria) this;
        }

        public Criteria andDayEqualTo(Integer value) {
            addCriterion("DAY =", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotEqualTo(Integer value) {
            addCriterion("DAY <>", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThan(Integer value) {
            addCriterion("DAY >", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("DAY >=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThan(Integer value) {
            addCriterion("DAY <", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThanOrEqualTo(Integer value) {
            addCriterion("DAY <=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayIn(List<Integer> values) {
            addCriterion("DAY in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotIn(List<Integer> values) {
            addCriterion("DAY not in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayBetween(Integer value1, Integer value2) {
            addCriterion("DAY between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotBetween(Integer value1, Integer value2) {
            addCriterion("DAY not between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
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