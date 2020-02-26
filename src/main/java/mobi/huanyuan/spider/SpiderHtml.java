package mobi.huanyuan.spider;

import lombok.Data;

import java.io.Serializable;

/**
 * 页面信息类.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 11:02
 */
@Data
public class SpiderHtml implements Serializable {
    /**
     * 页面URL
     */
    private String url;
    /**
     * 页面信息
     */
    private String html;
    /**
     * 爬取深度
     */
    private int depth;
}
