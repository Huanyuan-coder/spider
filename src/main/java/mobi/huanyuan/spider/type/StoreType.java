package mobi.huanyuan.spider.type;

/**
 * 存储类型.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 20:53
 */
public enum StoreType {
    /**
     * DB-MySQL-数据库存储
     */
    MYSQL("MYSQL"),
    /**
     * FILE-文件存储
     */
    FILE("FILE");

    private String type;

    private StoreType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
