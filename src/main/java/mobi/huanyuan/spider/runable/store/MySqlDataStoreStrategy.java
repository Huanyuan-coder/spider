package mobi.huanyuan.spider.runable.store;

import mobi.huanyuan.spider.bean.SpiderRecord;
import mobi.huanyuan.spider.mapper.SpiderRecordMapper;
import mobi.huanyuan.spider.runable.SpiderHtmlConfig;
import mobi.huanyuan.spider.type.StoreType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DB MySQL 存储数据实现.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/20 14:43
 */
@Service
public class MySqlDataStoreStrategy implements DataStoreStrategy {
    private static Logger logger = LoggerFactory.getLogger(MySqlDataStoreStrategy.class);

    static {
        DataStoreFactory.registerProvider(StoreType.MYSQL, MySqlDataStoreStrategy.class);
    }

    @Autowired
    private SpiderRecordMapper spiderRecordMapper;

    @Override
    public void store(SpiderHtmlConfig config, SpiderRecord record) {
        spiderRecordMapper.insert(record);
    }
}
