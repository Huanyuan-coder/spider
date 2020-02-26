package mobi.huanyuan.spider.runable;

import lombok.Builder;
import lombok.Getter;
import mobi.huanyuan.spider.type.StoreType;

import java.util.ArrayList;
import java.util.List;

/**
 * Description.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/23 15:06
 */
@Getter
@Builder
public class SpiderHtmlConfig {
    /**
     * 爬取页面最大深度
     */
    @Builder.Default
    public int maxDepth = 2;
    /**
     * 下载页面线程数
     */
    @Builder.Default
    public int minerHtmlThreadNum = 2;
    /**
     * 分析页面线程数
     */
    @Builder.Default
    public int minerParseThreadNum = 2;
    /**
     * 存储线程数
     */
    @Builder.Default
    public int minerStoreThreadNum = 2;
    /**
     * URL中包含的关键字
     */
    @Builder.Default
    public List<String> keys = new ArrayList<>();
    /**
     * 存储类型
     */
    @Builder.Default
    public StoreType storeType = StoreType.MYSQL;
    /**
     * 抓取文件本地存放位置
     */
    public String storeLocalPath;
}
