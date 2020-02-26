package mobi.huanyuan.spider.ui.vo;

import lombok.Builder;
import lombok.Data;

/**
 * ComboBox Value Domain.
 *
 * @author Jonathan L.(xingbing.lai@downjoy.com)
 * @version 1.0.0 -- Datetime: 2019/10/29 10:08
 */
@Data
@Builder
public class ComboVal {
    public static final ComboVal DEFAULT = ComboVal.builder().build();
    @Builder.Default
    private int id = 0;
    @Builder.Default
    private String desc = "无";
}
