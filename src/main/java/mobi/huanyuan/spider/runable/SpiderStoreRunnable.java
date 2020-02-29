package mobi.huanyuan.spider.runable;

import mobi.huanyuan.spider.Spider;
import mobi.huanyuan.spider.SpiderQueue;
import mobi.huanyuan.spider.bean.SpiderRecord;
import mobi.huanyuan.spider.runable.store.DataStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据存储任务.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 20:46
 */
public class SpiderStoreRunnable extends SpiderTask {
    private static final Logger logger = LoggerFactory.getLogger(SpiderStoreRunnable.class);

    private SpiderHtmlConfig config;

    public SpiderStoreRunnable(String taskName) {
        super(taskName);
    }

    public SpiderStoreRunnable(String taskName, SpiderHtmlConfig config) {
        super(taskName);
        this.config = config;
    }

    @Override
    public void exe() {
        while (!Spider.isStopping) {
            store();
        }
    }

    private synchronized void store() {
        SpiderRecord record = SpiderQueue.storePoll();
        if (null != record) {
            DataStoreFactory.store(config, record);
        }
    }
}
