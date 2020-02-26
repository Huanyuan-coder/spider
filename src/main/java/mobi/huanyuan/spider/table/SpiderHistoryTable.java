package mobi.huanyuan.spider.table;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Index;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 爬取历史.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/23 14:43
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "SPIDER_HISTORY")
@Data
public class SpiderHistoryTable extends BaseModel {
    @Column(name = "ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
    private Integer id;
    /**
     * 爬取页面最大深度
     */
    @Column(name = "MAX_DEPTH", type = MySqlTypeConstant.INT)
    public int maxDepth = 2;
    /**
     * 下载页面线程数
     */
    @Column(name = "HTML_THREAD_NUM", type = MySqlTypeConstant.INT)
    public int htmlThreadNum = 2;
    /**
     * 分析页面线程数
     */
    @Column(name = "PARSE_THREAD_NUM", type = MySqlTypeConstant.INT)
    public int parseThreadNum = 2;
    /**
     * 存储线程数
     */
    @Column(name = "STORE_THREAD_NUM", type = MySqlTypeConstant.INT)
    public int storeThreadNum = 2;
    /**
     * URL中包含的关键字
     */
    @Column(name = "KEY_WORDS", type = MySqlTypeConstant.VARCHAR, length = 128)
    @Index
    public String keyWords;
    /**
     * 页面URL
     */
    @Column(name = "URL", type = MySqlTypeConstant.VARCHAR)
    private String url;
    /**
     * 存储类型
     */
    @Column(name = "STORE_TYPE", type = MySqlTypeConstant.VARCHAR)
    public String storeType;
    /**
     * 抓取文件本地存放位置
     */
    @Column(name = "STORE_LOCAL_PATH", type = MySqlTypeConstant.VARCHAR)
    public String storeLocalPath;

    /**
     * 爬取日期，格式:yyyyMMdd,比如：20200220
     */
    @Index
    @Column(name = "DAY", type = MySqlTypeConstant.INT, length = 11)
    private int day;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", type = MySqlTypeConstant.DATETIME)
    private Date createTime;
}
