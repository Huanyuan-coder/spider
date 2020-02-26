package mobi.huanyuan.spider;

import mobi.huanyuan.spider.bean.SpiderRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 爬虫访问队列.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 10:54
 */
public class SpiderQueue {
    private static Logger logger = LoggerFactory.getLogger(SpiderQueue.class);

    /**
     * Set集合 保证每一个URL只访问一次
     */
    private static volatile Set<String> urlSet = new HashSet<>();
    /**
     * 待访问队列<br>
     * 爬取页面线程从这里取数据
     */
    private static volatile Queue<SpiderHtml> unVisited = new LinkedList<>();

    /**
     * 等待提取URL的分析页面队列<br>
     * 解析页面线程从这里取数据
     */
    private static volatile Queue<SpiderHtml> waitingMine = new LinkedList<>();

    /**
     * 存储队列<br>
     * 存储线程从这里取数据
     */
    private static volatile Queue<SpiderRecord> store = new LinkedList<>();

    /**
     * 添加到存储队列
     *
     * @param record 爬取页面
     */
    public synchronized static void addStore(SpiderRecord record) {
        store.add(record);
    }

    /**
     * 存储队列出队列
     *
     * @return 爬取页面
     */
    public synchronized static SpiderRecord storePoll() {
        return store.poll();
    }

    /**
     * 存储队列是否为空
     *
     * @return
     */
    public static boolean storeIsEmpty() {
        return store.isEmpty();
    }

    public static int getStoreSize() {
        return store.size();
    }

    /**
     * 添加到URL队列
     *
     * @param url
     */
    public synchronized static void addUrlSet(String url) {
        urlSet.add(url);
    }

    /**
     * 获得URL队列大小
     *
     * @return
     */
    public static int getUrlSetSize() {
        return urlSet.size();
    }

    /**
     * 添加到待访问队列，每个URL只访问一次
     *
     * @param spiderHtml
     */
    public synchronized static void addUnVisited(SpiderHtml spiderHtml) {
        if (null != spiderHtml && !urlSet.contains(spiderHtml.getUrl())) {
            logger.info("添加到待访问队列[{}] 当前第[{}]层 当前线程[{}]", spiderHtml.getUrl(), spiderHtml.getDepth(), Thread.currentThread().getName());
            unVisited.add(spiderHtml);
        }
    }

    /**
     * 待访问出队列
     *
     * @return
     */
    public synchronized static SpiderHtml unVisitedPoll() {
        return unVisited.poll();
    }

    public static int getUnVisitedSize() {
        return unVisited.size();
    }

    /**
     * 添加到等待提取URL的分析页面队列
     *
     * @param html
     */
    public synchronized static void addWaitingMine(SpiderHtml html) {
        waitingMine.add(html);
    }

    /**
     * 等待提取URL的分析页面出队列
     *
     * @return
     */
    public synchronized static SpiderHtml waitingMinePoll() {
        return waitingMine.poll();
    }

    /**
     * 等待提取URL的分析页面队列大小
     *
     * @return
     */
    public static int waitingMineSize() {
        return waitingMine.size();
    }

    /**
     * 等待提取URL的分析页面队列是否为空
     *
     * @return
     */
    public static boolean waitingMineIsEmpty() {
        return waitingMine.isEmpty();
    }

    /**
     * 判断待访问的队列是否为空
     *
     * @return true-空;false-非空
     */
    public static boolean unVisitedIsEmpty() {
        return unVisited.isEmpty();
    }

    public static void clean() {
        urlSet.clear();
        unVisited.clear();
        waitingMine.clear();
        store.clear();
    }
}
