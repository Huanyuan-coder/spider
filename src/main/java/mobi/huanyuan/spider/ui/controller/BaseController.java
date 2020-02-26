package mobi.huanyuan.spider.ui.controller;

import de.felixroske.jfxsupport.GUIState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * 控制器基类.
 *
 * @author Jonathan L.(xingbing.lai@downjoy.com)
 * @version 1.0.0 -- Datetime: 2019/10/29 14:24
 */
public abstract class BaseController {

    /**
     * 警告弹框
     *
     * @param content
     */
    public void alterWarn(String content) {
        alter(Alert.AlertType.WARNING, "Warning Dialog", content);
    }

    /**
     * 错误弹框
     *
     * @param content
     */
    public void alterError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    /**
     * 弹框
     *
     * @param content
     */
    public void alter(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void close(ActionEvent actionEvent) {
        GUIState.getStage().getScene().getWindow().hide();
    }
}
