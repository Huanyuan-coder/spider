package mobi.huanyuan.spider;

import mobi.huanyuan.spider.bean.SpiderHistory;
import mobi.huanyuan.spider.bean.SpiderHistoryExample;
import mobi.huanyuan.spider.mapper.SpiderHistoryMapper;
import mobi.huanyuan.spider.runable.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 爬虫.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 11:23
 */
@Component
public class Spider {
    private static Logger logger = LoggerFactory.getLogger(Spider.class);

    public static volatile boolean isStopping = false;
    public static long starTime;

    // 存储任务容器
    public static ConcurrentHashMap<String, SpiderTask> tasks = new ConcurrentHashMap<>();

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private ScheduledExecutorService scheduledExecutorService;
    @Autowired
    private SpiderHistoryMapper spiderHistoryMapper;

    public synchronized void start(SpiderHtmlConfig spiderHtmlConfig, String beginUrl) {
        logger.info("#####  Spider start begin ...");
        starTime = System.currentTimeMillis();
        SpiderHtml spiderHtml = new SpiderHtml();
        spiderHtml.setUrl(beginUrl);
        spiderHtml.setDepth(0);
        //程序启动，将第一个起始页面放入待访问队列。
        SpiderQueue.addUnVisited(spiderHtml);
        //将URL 添加到URL队列 保证每个URL只访问一次
        SpiderQueue.addUrlSet(spiderHtml.getUrl());

        //download
        for (int i = 0; i < spiderHtmlConfig.getMinerHtmlThreadNum(); i++) {
            SpiderHtmlRunnable minerHtmlRunnable = new SpiderHtmlRunnable("DownloadHtmlTask", spiderHtmlConfig);
            threadPoolExecutor.execute(minerHtmlRunnable);
        }
        // mine
        for (int i = 0; i < spiderHtmlConfig.getMinerParseThreadNum(); i++) {
            SpiderParseHtmlRunnable parseHtmlRunnable = new SpiderParseHtmlRunnable("ParseHtmlTask", spiderHtmlConfig);
            threadPoolExecutor.execute(parseHtmlRunnable);
        }
        // store
        for (int i = 0; i < spiderHtmlConfig.getMinerStoreThreadNum(); i++) {
            SpiderStoreRunnable minerStoreThread = new SpiderStoreRunnable("StoreHtmlTask", spiderHtmlConfig);
            threadPoolExecutor.execute(minerStoreThread);
        }
        logger.info("#####  Spider start over ...");

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (!isStopping) {
                logger.info("scheduleTask #### urlSetSize={}, unVisitedSize={}, waitingSize={}, storeSize={}",
                        SpiderQueue.getUrlSetSize(), SpiderQueue.getUnVisitedSize(),
                        SpiderQueue.waitingMineSize(), SpiderQueue.getStoreSize());
                if (SpiderQueue.unVisitedIsEmpty()
                        && SpiderQueue.waitingMineIsEmpty()
                        && SpiderQueue.storeIsEmpty()) {
                    stop();
                }
            }
        }, 10, 1, TimeUnit.SECONDS);

        // 记录一次爬取历史
        SpiderHistoryExample example = new SpiderHistoryExample();
        SpiderHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andDayEqualTo(Integer.parseInt(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now())))
                .andKeyWordsEqualTo(StringUtils.join(spiderHtmlConfig.getKeys(), ","));
        List<SpiderHistory> history = spiderHistoryMapper.selectByExample(example);
        if (null == history || history.size() <= 0) {
            SpiderHistory spiderHistory = new SpiderHistory();
            spiderHistory.setKeyWords(StringUtils.join(spiderHtmlConfig.getKeys(), ","));
            spiderHistory.setMaxDepth(spiderHtmlConfig.getMaxDepth());
            spiderHistory.setHtmlThreadNum(spiderHtmlConfig.getMinerHtmlThreadNum());
            spiderHistory.setParseThreadNum(spiderHtmlConfig.getMinerParseThreadNum());
            spiderHistory.setStoreThreadNum(spiderHtmlConfig.getMinerStoreThreadNum());
            spiderHistory.setStoreLocalPath(spiderHtmlConfig.getStoreLocalPath());
            spiderHistory.setStoreType(spiderHtmlConfig.getStoreType().getType());
            spiderHistory.setUrl(beginUrl);
            spiderHistory.setCreateTime(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            spiderHistory.setDay(Integer.parseInt(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now())));
            spiderHistoryMapper.insert(spiderHistory);
        }
    }

    public synchronized void stop() {
        if (isStopping) {
            logger.warn("Spider is not running!!!!");
            return;
        }
        isStopping = true;
        for (SpiderTask task : tasks.values()) {
            try {
                if (threadPoolExecutor.getQueue().contains(task)) {
                    Thread.State state = task.getThreadStatus();
                    if (Thread.State.RUNNABLE == state) {
                        task.setInterrupted(task.getTaskInThread());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SpiderQueue.clean();
        logger.info("程序结束。。。。。。当前线程[{}]", Thread.currentThread().getName());
        long endTime = System.currentTimeMillis();
        logger.info("已经访问队列URL大小[{}]当前线程[{}]", SpiderQueue.getUrlSetSize(), Thread.currentThread().getName());
        logger.info("用时[{}ms]当前线程[{}]", endTime - starTime, Thread.currentThread().getName());
    }
}
