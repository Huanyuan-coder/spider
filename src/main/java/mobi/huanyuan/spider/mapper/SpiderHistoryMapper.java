package mobi.huanyuan.spider.mapper;

import java.util.List;
import mobi.huanyuan.spider.bean.SpiderHistory;
import mobi.huanyuan.spider.bean.SpiderHistoryExample;
import org.apache.ibatis.annotations.Param;

public interface SpiderHistoryMapper {
    long countByExample(SpiderHistoryExample example);

    int deleteByExample(SpiderHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpiderHistory record);

    int insertSelective(SpiderHistory record);

    List<SpiderHistory> selectByExample(SpiderHistoryExample example);

    SpiderHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpiderHistory record, @Param("example") SpiderHistoryExample example);

    int updateByExample(@Param("record") SpiderHistory record, @Param("example") SpiderHistoryExample example);

    int updateByPrimaryKeySelective(SpiderHistory record);

    int updateByPrimaryKey(SpiderHistory record);
}