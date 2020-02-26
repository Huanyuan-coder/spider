package mobi.huanyuan.spider.table;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Index;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 爬虫抓取记录.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/20 10:48
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "SPIDER_RECORD")
@Data
public class SpiderRecordTable extends BaseModel {
    @Column(name = "ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
    private Integer id;
    /**
     * 页面URL
     */
    @Column(name = "URL", type = MySqlTypeConstant.VARCHAR)
    private String url;
    /**
     * 页面信息
     */
    @Column(name = "HTML", type = MySqlTypeConstant.LONGTEXT)
    private String html;
    /**
     * 爬取深度
     */
    @Column(name = "DEPTH", type = MySqlTypeConstant.INT, length = 11)
    private int depth;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", type = MySqlTypeConstant.DATETIME)
    private Date createTime;
    /**
     * 关键字，如果是多个，用半角逗号分隔开
     */
    @Index
    @Column(name = "KEY_WORDS", type = MySqlTypeConstant.VARCHAR, length = 128)
    private String keys;
    /**
     * 爬取日期，格式:yyyyMMdd,比如：20200220
     */
    @Index
    @Column(name = "DAY", type = MySqlTypeConstant.INT, length = 11)
    private int day;
}
