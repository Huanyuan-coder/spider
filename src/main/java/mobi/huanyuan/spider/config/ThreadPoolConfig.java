package mobi.huanyuan.spider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池配置.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/18 11:35
 */
@Configuration
public class ThreadPoolConfig {
    @Autowired
    private SpiderConfig spiderConfig;

    @Bean(name = "threadPoolExecutor")
    public ThreadPoolExecutor threadPoolTaskExecutor() {
        // 处理器个数
        int PROCESS_NUM = Runtime.getRuntime().availableProcessors();

        // 根据处理器个数定义线程个数
        int THREAD_NUM = Math.max(PROCESS_NUM, 4) * 4;

        // 任务队列大小
        int DISPENSE_MAX_WAITTING_THREAD_NUM = Short.MAX_VALUE >> 1;// 16383

        // 最大任务执行时间，时间上限
        int MAXIMUM_TASK_EXECUTION_TIME = 5;

        return new ThreadPoolExecutor(
                spiderConfig.getCorePoolSize() == 0 ? THREAD_NUM : spiderConfig.getCorePoolSize(),
                spiderConfig.getMaxPoolSize() == 0 ? THREAD_NUM : spiderConfig.getMaxPoolSize(),
                spiderConfig.getKeepAliveSeconds(), TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(DISPENSE_MAX_WAITTING_THREAD_NUM), new NameThreadFactory("SpiderTask"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    // 定时任务，定时删除任务执行时间大于5秒钟的任务
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(1,
                new NameThreadFactory("ScheduledTask"), new ThreadPoolExecutor.AbortPolicy());
    }

    static class NameThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String threadName;

        public NameThreadFactory(String threadName) {
            this.threadName = threadName;
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, threadName + threadNumber.getAndIncrement());
        }
    }

}
