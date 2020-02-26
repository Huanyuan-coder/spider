package mobi.huanyuan.spider.table;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配置管理.
 *
 * @author Jonathan L.(xingbing.lai@downjoy.com)
 * @version 1.0.0 -- Datetime: 2019/11/1 17:07
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "SETTING")
@Data
public class SettingTable extends BaseModel {
    public static final int DEFAULT_ID = 1;
    @Column(name = "ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
    private Integer id = DEFAULT_ID;

    @Column(name = "LOCAL_PATH", type = MySqlTypeConstant.VARCHAR)
    private String localPath;// 存储类型是文件，爬取后将文件保存在这个目录
}
