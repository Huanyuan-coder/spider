package mobi.huanyuan.spider.config;

import lombok.Data;
import mobi.huanyuan.spider.type.StoreType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 爬虫配置.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 11:10
 */
@Data
@ConfigurationProperties(prefix = "huanyuan.spider")
public class SpiderConfig {
    //=================================================
    //  线程池配置
    //=================================================
    /**
     * 核心线程池大小
     */
    private int corePoolSize;

    /**
     * 最大可创建的线程数
     */
    private int maxPoolSize;

    /**
     * 队列最大长度
      */
    private int queueCapacity;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds;
}
