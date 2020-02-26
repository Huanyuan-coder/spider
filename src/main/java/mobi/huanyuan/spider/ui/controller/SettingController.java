package mobi.huanyuan.spider.ui.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import mobi.huanyuan.spider.Constants;
import mobi.huanyuan.spider.SpiderApplication;
import mobi.huanyuan.spider.bean.Setting;
import mobi.huanyuan.spider.mapper.SettingMapper;
import mobi.huanyuan.spider.ui.view.DashBoardView;
import mobi.huanyuan.spider.ui.view.SettingView;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 设置界面.
 *
 * @author Jonathan L.(xingbing.lai@downjoy.com)
 * @version 1.0.0 -- Datetime: 2019/11/1 16:19
 */
@FXMLController
public class SettingController extends BaseController implements Initializable {
    private static Logger logger = LoggerFactory.getLogger(SettingController.class);
    @FXML
    private TextField localPath;

    @Autowired
    private SettingMapper settingMapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Setting setting = settingMapper.selectByPrimaryKey(Constants.SettingDefaultId);
        final DirectoryChooser fileChooser = new DirectoryChooser();
        String workDir = System.getProperties().getProperty("user.dir");
        String storeLocalPath = StringUtils.isBlank(setting.getLocalPath()) ? workDir : setting.getLocalPath();
        localPath.setText(storeLocalPath);
        localPath.setOnMouseClicked(event -> {
            configureFileChooser(fileChooser, storeLocalPath);
            File file = fileChooser.showDialog(localPath.getParent().getScene().getWindow());
            if (file != null) {
                logger.info("localPath: {}", file);
                localPath.setText(file.getAbsolutePath());
            }
        });

    }

    private static void configureFileChooser(final DirectoryChooser fileChooser, String defaultPath) {
        fileChooser.setTitle("选择文件夹");
        if (StringUtils.isNotBlank(defaultPath)) {
            File file = new File(defaultPath);
            if (file.exists()) {
                fileChooser.setInitialDirectory(file);
            }
        }
    }

    public void saveSettings() {
        logger.info("localPath = {}", localPath.getText());

        Platform.runLater(() -> {
            Setting setting = new Setting();
            setting.setId(Constants.SettingDefaultId);
            setting.setLocalPath(localPath.getText());

            settingMapper.updateByPrimaryKeySelective(setting);
            close(null);
            SpiderApplication.switchView(SettingView.class, DashBoardView.class, null);
        });
    }
}
