package mobi.huanyuan.spider.bean;

import java.util.Date;

public class SpiderHistory {
    private Integer id;

    private Integer maxDepth;

    private Integer htmlThreadNum;

    private Integer parseThreadNum;

    private Integer storeThreadNum;

    private String keyWords;

    private String url;

    private String storeType;

    private String storeLocalPath;

    private Integer day;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(Integer maxDepth) {
        this.maxDepth = maxDepth;
    }

    public Integer getHtmlThreadNum() {
        return htmlThreadNum;
    }

    public void setHtmlThreadNum(Integer htmlThreadNum) {
        this.htmlThreadNum = htmlThreadNum;
    }

    public Integer getParseThreadNum() {
        return parseThreadNum;
    }

    public void setParseThreadNum(Integer parseThreadNum) {
        this.parseThreadNum = parseThreadNum;
    }

    public Integer getStoreThreadNum() {
        return storeThreadNum;
    }

    public void setStoreThreadNum(Integer storeThreadNum) {
        this.storeThreadNum = storeThreadNum;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType == null ? null : storeType.trim();
    }

    public String getStoreLocalPath() {
        return storeLocalPath;
    }

    public void setStoreLocalPath(String storeLocalPath) {
        this.storeLocalPath = storeLocalPath == null ? null : storeLocalPath.trim();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}