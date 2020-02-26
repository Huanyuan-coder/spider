package mobi.huanyuan.spider.mapper;

import java.util.List;
import mobi.huanyuan.spider.bean.Setting;
import mobi.huanyuan.spider.bean.SettingExample;
import org.apache.ibatis.annotations.Param;

public interface SettingMapper {
    long countByExample(SettingExample example);

    int deleteByExample(SettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Setting record);

    int insertSelective(Setting record);

    List<Setting> selectByExample(SettingExample example);

    Setting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Setting record, @Param("example") SettingExample example);

    int updateByExample(@Param("record") Setting record, @Param("example") SettingExample example);

    int updateByPrimaryKeySelective(Setting record);

    int updateByPrimaryKey(Setting record);
}