package mobi.huanyuan.spider.runable.store;

import mobi.huanyuan.spider.bean.SpiderRecord;
import mobi.huanyuan.spider.runable.SpiderHtmlConfig;
import mobi.huanyuan.spider.type.StoreType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 文件存储实现.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/20 14:42
 */
@Service
public class FileDataStoreStrategy implements DataStoreStrategy {
    private static Logger logger = LoggerFactory.getLogger(FileDataStoreStrategy.class);

    static {
        DataStoreFactory.registerProvider(StoreType.FILE, FileDataStoreStrategy.class);
    }

    @Override
    public void store(SpiderHtmlConfig config, SpiderRecord record) {
        if (null == record || StringUtils.isBlank(record.getHtml())) {
            return;
        }
        String title = fileName(record.getUrl());
        if (title == null || title.length() > 255) {
            return;
        }
        storeHtmlToLocal(config.getStoreLocalPath(), title, record.getHtml());
        logger.info("保存数据文件完成，当前线程[{}]", Thread.currentThread().getName());
    }

    /**
     * 文件名不能包含下列任何字符:<br>
     * \/:*?"<>|
     *
     * @param title 标题
     * @return 去掉文件名不能包含的字符
     */
    public String fileName(String title) {
        return title
                .replaceAll("\\\\", "")
                .replaceAll("/", "")
                .replaceAll(":", "")
                .replaceAll("\\*", "")
                .replaceAll("\\?", "")
                .replaceAll("\"", "")
                .replaceAll("<", "")
                .replaceAll(">", "")
                .replaceAll("\\|", "");
    }

    /**
     * 将html写入本地文件
     *
     * @param title   文件名
     * @param content 内容
     */
    private void storeHtmlToLocal(String local, String title, String content) {
        Writer writer = null;
        try {
            String path = local + DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
            makeDir(path);
            writer = new OutputStreamWriter(new FileOutputStream(new File(path + File.separator + title)), StandardCharsets.UTF_8);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 创建存储目录
     *
     * @param path 存储目录
     */
    private void makeDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
            logger.info("创建存储目录[{}]", path);
        }
    }
}
