package mobi.huanyuan.spider.runable;

import mobi.huanyuan.spider.Spider;
import mobi.huanyuan.spider.SpiderHtml;
import mobi.huanyuan.spider.SpiderQueue;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 解析页面任务.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 17:11
 */
public class SpiderParseHtmlRunnable extends SpiderTask {
    private static final Logger logger = LoggerFactory.getLogger(SpiderParseHtmlRunnable.class);

    private SpiderHtmlConfig config;

    public SpiderParseHtmlRunnable(String taskName) {
        super(taskName);
    }

    public SpiderParseHtmlRunnable(String taskName, SpiderHtmlConfig config) {
        super(taskName);
        this.config = config;
    }

    @Override
    public void exe() {
        while (!Spider.isStopping) {
            parse();
        }
    }

    private synchronized void parse() {
        SpiderHtml html = SpiderQueue.waitingMinePoll(); // 等待提取URL的分析页面出队列
        if (null == html || StringUtils.isBlank(html.getHtml())) {
            return;
        }
        //当前页面深度<爬取深度 取出当前页面全部URL
        if (html.getDepth() < config.getMaxDepth()) {
            logger.debug("获取页面[{}]下所有URL。。。。。。 当前线程 [{}]", html.getUrl(), Thread.currentThread().getName());
            Set<String> urls = getAllUrl(html);
            for (String url : urls) {
                if (null == url || url.equals("")) {
                    continue;
                }
                if (url.substring(url.length() - 1).equals("/")) {
                    url = url.substring(0, url.length() - 1);
                }

                SpiderHtml minerUrl = new SpiderHtml();
                minerUrl.setUrl(url);
                minerUrl.setDepth(html.getDepth() + 1); // 爬取深度+1
                // 判断URL列表是否包含关键字
                if (!checkKeys(url, config.getKeys())) {
                    continue;
                }
                // 添加到待访问队列，每个URL只访问一次
                SpiderQueue.addUnVisited(minerUrl);
                // 将页面URL 添加到URL队列 保证每个URL只访问一次
                SpiderQueue.addUrlSet(minerUrl.getUrl());
            }
        }
    }

    /**
     * 获取URL
     *
     * @param html
     * @return URL set
     */
    public Set<String> getAllUrl(SpiderHtml html) {
        Set<String> urls = new HashSet<>();
        try {
            Document document = Jsoup.parse(html.getHtml());
            Elements hrefs = document.select("a[href]");
            for (Element href : hrefs) {
                urls.add(href.attr("href"));
            }
        } catch (Exception e) {
            logger.warn("获取URL出现异常，异常URL[{}], 异常信息[{}]", html.getUrl(), e.getMessage());
        }
        return urls;
    }

    /**
     * URL列表是否包含关键字
     *
     * @param key  关键字
     * @param keys URL列表
     * @return true-是;false-否
     */
    public boolean checkKeys(String key, List<String> keys) {
        boolean flag = false;
        for (String k : keys) {
            if (key.contains(k)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
