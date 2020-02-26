package mobi.huanyuan.spider.ui.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import mobi.huanyuan.spider.Constants;
import mobi.huanyuan.spider.Spider;
import mobi.huanyuan.spider.SpiderApplication;
import mobi.huanyuan.spider.SpiderQueue;
import mobi.huanyuan.spider.bean.Setting;
import mobi.huanyuan.spider.bean.SpiderHistory;
import mobi.huanyuan.spider.bean.SpiderHistoryExample;
import mobi.huanyuan.spider.mapper.SettingMapper;
import mobi.huanyuan.spider.mapper.SpiderHistoryMapper;
import mobi.huanyuan.spider.runable.SpiderHtmlConfig;
import mobi.huanyuan.spider.type.StoreType;
import mobi.huanyuan.spider.ui.view.SettingView;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.StatusBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 主界面控制器.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/22 14:27
 */
@FXMLController
public class DashBoardController extends BaseController implements Initializable {
    private static Logger logger = LoggerFactory.getLogger(DashBoardController.class);

    private Image rootIcon;
    private Image dayIcon;
    private Image keyWordIcon;
    private Image demoIcon;

    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem setting;
    @FXML
    private MenuItem about;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private TextField url;
    @FXML
    private TextField keys;
    @FXML
    private TextField maxDepth;
    @FXML
    private TextField htmlThreadNum;
    @FXML
    private TextField parseThreadNum;
    @FXML
    private TextField storeThreadNum;
    @FXML
    private ComboBox<StoreType> storeType;
    @FXML
    private TextField localPath;

    @FXML
    private Button startBtn;
    @FXML
    private Button stopBtn;

    @FXML
    private StatusBar statusBar;

    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private SpiderHistoryMapper spiderHistoryMapper;
    @Autowired
    private Spider spider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootIcon = new Image(this.getClass().getResourceAsStream("/images/history.png"), 25, 25, false, false);
        dayIcon = new Image(this.getClass().getResourceAsStream("/images/date.png"), 25, 25, false, false);
        keyWordIcon = new Image(this.getClass().getResourceAsStream("/images/keyword.png"), 25, 25, false, false);
        demoIcon = new Image(this.getClass().getResourceAsStream("/images/demo.png"), 25, 25, false, false);

        initMenus();

        ObservableList<StoreType> storeValues = FXCollections.observableArrayList(StoreType.values());
        storeType.getItems().addAll(storeValues);
        storeType.getSelectionModel().select(StoreType.MYSQL);

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

        ChangeListener<String> numberValidListener = (observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                maxDepth.setText(newValue.replaceAll("[^\\d]", ""));
            }
        };
        maxDepth.textProperty().addListener(numberValidListener);
        htmlThreadNum.textProperty().addListener(numberValidListener);
        parseThreadNum.textProperty().addListener(numberValidListener);
        storeThreadNum.textProperty().addListener(numberValidListener);

        initTreeView();
    }

    private void initMenus() {
        exit.setOnAction(actionEvent -> Platform.exit());
        setting.setOnAction(event -> {
            SpiderApplication.showView(SettingView.class, Modality.WINDOW_MODAL);
        });
        about.setOnAction(event -> {
            Dialog<?> dialog = new Dialog<>();
            dialog.setTitle("关于幻猿·简易爬虫");
            dialog.setContentText("\n\t一个简易的爬虫系统。\n\n" +
                    "\t基于SpringBoot2、MyBatis、JavaFx技术实现。\n\n" +
                    "\t\t\t\t\t\t\tversion: 0.0.1\n\n");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
            closeButton.managedProperty().bind(closeButton.visibleProperty());
            closeButton.setVisible(false);
            dialog.showAndWait();
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

    //====================================================================================
    //  Tree
    //====================================================================================

    /**
     * 设置TreeView
     */
    public void initTreeView() {
        TreeItem<String> root = new TreeItem<>("近30天记录", new ImageView(rootIcon));
        root.setExpanded(true);
        treeView.setRoot(root);

        SpiderHistoryExample example = new SpiderHistoryExample();
        SpiderHistoryExample.Criteria criteria = example.createCriteria();

        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        criteria.andDayGreaterThanOrEqualTo(Integer.parseInt(DateTimeFormatter.BASIC_ISO_DATE.format(thirtyDaysAgo)));
        example.setOrderByClause("DAY DESC");
        List<SpiderHistory> historyList = spiderHistoryMapper.selectByExample(example);

        for (SpiderHistory history : historyList) {
            TreeItem<String> keyWordsNode = new TreeItem<>(history.getKeyWords(), new ImageView(keyWordIcon));
            boolean found = false;
            for (TreeItem<String> dayNode : root.getChildren()) {
                if (dayNode.getValue().contentEquals("" + history.getDay())) {
                    dayNode.getChildren().add(keyWordsNode);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> dayNode = new TreeItem<>(
                        "" + history.getDay(),
                        new ImageView(dayIcon)
                );
                root.getChildren().add(dayNode);
                dayNode.getChildren().add(keyWordsNode);
            }
        }

        TreeItem<String> day = new TreeItem<>("Demo", new ImageView(demoIcon));
        Arrays.asList("Java", "Python", "JavaScript", "JavaFx", "SpringBoot").forEach(s -> {
            TreeItem<String> node = new TreeItem<>(s, new ImageView(keyWordIcon));
            day.getChildren().add(node);
        });
        root.getChildren().add(day);
    }

    /**
     * TreeView 点击事件
     */
    public void treeViewClick() {
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
        if (null != selectedItem && selectedItem.isLeaf()) {
            fillData(selectedItem.getValue(), selectedItem.getParent().getValue());
        }
    }

    /**
     * 填充数据
     */
    private void fillData(String keyword, String day) {
        String maxDepth1 = "2";
        String htmlThreadNum1 = "2";
        String parseThreadNum1 = "2";
        String storeThreadNum1 = "2";
        String storeLocalPath1 = System.getProperties().getProperty("user.dir");
        String storeType1 = StoreType.MYSQL.getType();
        String url1 = "https://stackoverflow.com/questions";
        if (!"Demo".equals(day)) {
            SpiderHistoryExample example = new SpiderHistoryExample();
            SpiderHistoryExample.Criteria criteria = example.createCriteria();
            criteria.andDayEqualTo(Integer.parseInt(day)).andKeyWordsEqualTo(keyword);
            SpiderHistory history = spiderHistoryMapper.selectByExample(example).get(0);
            if (null != history) {
                maxDepth1 = "" + history.getMaxDepth();
                htmlThreadNum1 = "" + history.getHtmlThreadNum();
                parseThreadNum1 = "" + history.getParseThreadNum();
                storeThreadNum1 = "" + history.getStoreThreadNum();
                storeLocalPath1 = history.getStoreLocalPath();
                storeType1 = history.getStoreType();
                url1 = history.getUrl();
            }
        }
        url.setText(url1);
        keys.setText(keyword);
        maxDepth.setText(maxDepth1);
        htmlThreadNum.setText(htmlThreadNum1);
        parseThreadNum.setText(parseThreadNum1);
        storeThreadNum.setText(storeThreadNum1);
        storeType.getSelectionModel().select(StoreType.valueOf(storeType1));
        localPath.setText(storeLocalPath1);
    }

    //====================================================================================
    //  StatusBar
    //====================================================================================
    private void startTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (!Spider.isStopping) {
                    Thread.sleep(200);
                    updateMessage(String.format("已爬取页面:%d | 待爬取页面:%d | 待分析页面:%d | 待存储页面:%d",
                            SpiderQueue.getUrlSetSize(), SpiderQueue.getUnVisitedSize(),
                            SpiderQueue.waitingMineSize(), SpiderQueue.getStoreSize()));
                }
                done();
                return null;
            }
        };

        statusBar.textProperty().bind(task.messageProperty());
        statusBar.progressProperty().bind(task.progressProperty());

        // remove bindings again
        task.setOnSucceeded(event -> {
            statusBar.textProperty().unbind();
            statusBar.progressProperty().unbind();
        });

        new Thread(task).start();
    }

    //====================================================================================
    //  Button
    //====================================================================================
    public void start() {
        SpiderHtmlConfig spiderHtmlConfig = SpiderHtmlConfig.builder()
                .keys(Arrays.asList(StringUtils.replace(keys.getText(), "，", ",").split(",")))
                .maxDepth(Integer.parseInt(maxDepth.getText()))
                .minerHtmlThreadNum(Integer.parseInt(htmlThreadNum.getText()))
                .minerParseThreadNum(Integer.parseInt(parseThreadNum.getText()))
                .minerStoreThreadNum(Integer.parseInt(storeThreadNum.getText()))
                .storeType(storeType.getSelectionModel().getSelectedItem())
                .storeLocalPath(localPath.getText())
                .build();

//        Spider spider = BeanManager.getBean(Spider.class);
        spider.start(spiderHtmlConfig, url.getText());

        startBtn.setDisable(true);
        // start statusBar
        startTask();
    }

    public void stop() {
        stopBtn.setDisable(true);
//        Spider spider = BeanManager.getBean(Spider.class);
        spider.stop();
        startBtn.setDisable(false);
        stopBtn.setDisable(false);
        // stop statusBar
        statusBar.textProperty().unbind();
        statusBar.progressProperty().unbind();
        statusBar.setProgress(0);
    }
}
