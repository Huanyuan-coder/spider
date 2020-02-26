package mobi.huanyuan.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanManager implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(BeanManager.class);
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@ BeanManager init start ...");
        if (BeanManager.context == null) {
            BeanManager.context = applicationContext;
        }
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@ BeanManager init finish ...");
    }

    /**
     * 获取applicationContext
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name bean name
     * @return Spring bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz class
     * @param <T>   Spring bean
     * @return Spring bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  spring bean name
     * @param clazz spring bean class
     * @param <T>   spring bean
     * @return spring bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}