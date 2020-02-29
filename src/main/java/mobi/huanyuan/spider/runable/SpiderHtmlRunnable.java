package mobi.huanyuan.spider.runable;

import mobi.huanyuan.spider.Spider;
import mobi.huanyuan.spider.SpiderHtml;
import mobi.huanyuan.spider.SpiderQueue;
import mobi.huanyuan.spider.bean.SpiderRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 抓取页面任务.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 11:43
 */
public class SpiderHtmlRunnable extends SpiderTask {
    private static final Logger logger = LoggerFactory.getLogger(SpiderHtmlRunnable.class);
    private SpiderHtmlConfig config;

    public SpiderHtmlRunnable(String taskName) {
        super(taskName);
    }

    public SpiderHtmlRunnable(String taskName, SpiderHtmlConfig config) {
        super(taskName);
        this.config = config;
    }

    @Override
    public void exe() {
        while (!Spider.isStopping) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            minerHtml();
        }
    }

    public synchronized void minerHtml() {
        SpiderHtml minerUrl = SpiderQueue.unVisitedPoll(); // 待访问出队列。
        try {
            //判断当前页面爬取深度
            if (null == minerUrl || StringUtils.isBlank(minerUrl.getUrl()) || minerUrl.getDepth() > config.getMaxDepth()) {
                return;
            }
            //判断爬取页面URL是否包含http
            if (!minerUrl.getUrl().startsWith("http")) {
                logger.warn("当前爬取URL[{}]没有http", minerUrl.getUrl());
                return;
            }
            logger.debug("当前爬取页面[{}]爬取深度[{}] 当前线程 [{}]", minerUrl.getUrl(), minerUrl.getDepth(), Thread.currentThread().getName());
            Document doc = Jsoup.connect(minerUrl.getUrl())
                    .header("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13")//配置模拟浏览器
                    .timeout(5000)//5s超时
                    .get();
            String page = doc.html();

            // 添加到继续爬取队列
            SpiderHtml spiderHtml = new SpiderHtml();
            spiderHtml.setUrl(minerUrl.getUrl());
            spiderHtml.setHtml(page);
            spiderHtml.setDepth(minerUrl.getDepth());
            SpiderQueue.addWaitingMine(spiderHtml);

            // 添加到存储队列
            Date now = new Date();
            SpiderRecord record = new SpiderRecord();
            record.setUrl(minerUrl.getUrl());
            record.setDepth(minerUrl.getDepth());
            record.setHtml(page);
            record.setDay(Integer.parseInt(DateFormatUtils.format(now, "yyyyMMdd")));
            record.setCreateTime(now);
            record.setKeyWords(StringUtils.join(config.getKeys().toArray(), ","));
            SpiderQueue.addStore(record);
        } catch (Exception e) {
            logger.warn("爬取页面失败 URL [{}], Error: [{}]", minerUrl.getUrl(), e.getMessage());
        }
    }
}
