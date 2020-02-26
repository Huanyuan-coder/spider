package mobi.huanyuan.spider.table;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Description.
 *
 * @author Jonathan L.(xingbing.lai@gmail.com)
 * @version 1.0.0 -- Datetime: 2020/2/20 11:12
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "TEST_INFO")
@Data
public class TestInfoTable extends BaseModel {
    @Column(name = "ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
    private Integer id;
    @Column(name = "CREATE_TIME", type = MySqlTypeConstant.DATETIME)
    private Date createTime;
}
