package mobi.huanyuan.spider.runable.store;

import mobi.huanyuan.spider.bean.SpiderRecord;
import mobi.huanyuan.spider.runable.SpiderHtmlConfig;

/**
 * 数据存储接口.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/20 14:40
 */
public interface DataStoreStrategy {
    /**
     * 存储数据
     *
     * @param config
     * @param record
     */
    void store(SpiderHtmlConfig config, SpiderRecord record);
}
