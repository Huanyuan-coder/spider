package mobi.huanyuan.spider;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mobi.huanyuan.spider.bean.Setting;
import mobi.huanyuan.spider.config.SpiderConfig;
import mobi.huanyuan.spider.mapper.SettingMapper;
import mobi.huanyuan.spider.ui.CustomSplash;
import mobi.huanyuan.spider.ui.view.DashBoardView;
import mobi.huanyuan.spider.ui.vo.ViewEvent;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
@EnableConfigurationProperties(SpiderConfig.class)
@MapperScan(value = {
        "mobi.huanyuan.spider.mapper",
        "com.gitee.sunchenbin.mybatis.actable.dao.*"
})
@ComponentScan(value = {
        "mobi.huanyuan.spider",
        "com.gitee.sunchenbin.mybatis.actable.manager.*"
})
public class SpiderApplication extends AbstractJavaFxApplicationSupport {
    private static Logger logger = LoggerFactory.getLogger(SpiderApplication.class);

    private static EventBus bus = EventBus.builder().build();

    public static void main(String[] args) {
        launch(SpiderApplication.class, DashBoardView.class, new CustomSplash(), args);
    }

    public static void switchView(Class<? extends AbstractFxmlView> from, Class<? extends AbstractFxmlView> to, Object object) {
        try {
            logger.debug("从 {} 跳转到 {}", from, to);
            StopWatch started = StopWatch.createStarted();
            AbstractFxmlView fromViewer = BeanManager.getBean(from);
            AbstractFxmlView toViewer = BeanManager.getBean(to);

            if (!bus.isRegistered(fromViewer.getPresenter()) && hasSubscribe(fromViewer.getPresenter())) {
                bus.register(fromViewer.getPresenter());
                logger.info("registered:{}", fromViewer.getPresenter().getClass());
            }

            if (!bus.isRegistered(toViewer.getPresenter()) && hasSubscribe(toViewer.getPresenter())) {
                bus.register(toViewer.getPresenter());
                logger.info("registered:{}", toViewer.getPresenter().getClass());
            }

            if (bus.isRegistered(fromViewer.getPresenter())) {
                logger.debug("发布隐藏事件");
                bus.post(new ViewEvent(ViewEvent.ViewEvenType.hide, fromViewer, fromViewer.getPresenter()));
            }

            Platform.runLater(() -> {
                AbstractJavaFxApplicationSupport.showView(to);
                if (bus.isRegistered(toViewer.getPresenter())) {
                    logger.debug("发布显示事件");
                    bus.post(new ViewEvent(ViewEvent.ViewEvenType.show, toViewer, toViewer.getPresenter()));
                }

                if (object != null) {
                    logger.debug("跳转参数:{}", object);
                    bus.post(object);
                }

                logger.debug("跳转页面耗时:{}", started.getTime());
            });


        } catch (Exception e) {
            logger.error("跳转页面异常", e);
        }
    }

    private static boolean hasSubscribe(Object object) {
        Method[] subscribes = MethodUtils.getMethodsWithAnnotation(object.getClass(), Subscribe.class);
        return subscribes != null && subscribes.length > 0;
    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        stage.setWidth(900);
        stage.setHeight(670);
        stage.setResizable(false);
        stage.setTitle("幻猿·简易爬虫");
        stage.setOnCloseRequest(event -> System.exit(0));

        // 检查基础配置
        SettingMapper settingMapper = BeanManager.getBean(SettingMapper.class);
        Setting setting = settingMapper.selectByPrimaryKey(Constants.SettingDefaultId);
        if (null == setting) {
            setting = new Setting();
            setting.setId(Constants.SettingDefaultId);
            String workDir = System.getProperties().getProperty("user.dir");
            setting.setLocalPath(workDir);
            settingMapper.insert(setting);
        }
    }

    @Override
    public Collection<Image> loadDefaultIcons() {
        return Collections.singletonList(new Image(this.getClass().getResourceAsStream("/images/logo.jpg")));
    }
}
