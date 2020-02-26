package mobi.huanyuan.spider.runable.store;

import mobi.huanyuan.spider.BeanManager;
import mobi.huanyuan.spider.bean.SpiderRecord;
import mobi.huanyuan.spider.runable.SpiderHtmlConfig;
import mobi.huanyuan.spider.type.StoreType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据存储方式管理工厂.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/20 14:45
 */
public class DataStoreFactory {
    private static Logger logger = LoggerFactory.getLogger(DataStoreFactory.class);

    // 策略映射map
    private static final Map<StoreType, Class<?>> providers = new HashMap<>();

    // 注册可用存储策略
    public static void registerProvider(StoreType storeType, Class<?> provider) {
        providers.put(storeType, provider);
    }

    public static boolean store(SpiderHtmlConfig config, SpiderRecord spiderRecord) {
        Class<?> providerClazz = providers.get(config.getStoreType());
        if (null == providerClazz) {
            logger.warn("Store strategy is null.[StoreType={}]", config.getStoreType());
            return false;
        }
        Object bean = BeanManager.getBean(providerClazz);
        if (bean instanceof DataStoreStrategy) {
            DataStoreStrategy dataStoreStrategy = (DataStoreStrategy) bean;
            dataStoreStrategy.store(config, spiderRecord);
            return true;
        }
        logger.error("Not Class with DataStoreStrategy: {}", providerClazz.getName());
        return false;
    }
}
