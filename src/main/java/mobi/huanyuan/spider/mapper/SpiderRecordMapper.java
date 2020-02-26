package mobi.huanyuan.spider.mapper;

import java.util.List;
import mobi.huanyuan.spider.bean.SpiderRecord;
import mobi.huanyuan.spider.bean.SpiderRecordExample;
import org.apache.ibatis.annotations.Param;

public interface SpiderRecordMapper {
    long countByExample(SpiderRecordExample example);

    int deleteByExample(SpiderRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpiderRecord record);

    int insertSelective(SpiderRecord record);

    List<SpiderRecord> selectByExampleWithBLOBs(SpiderRecordExample example);

    List<SpiderRecord> selectByExample(SpiderRecordExample example);

    SpiderRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpiderRecord record, @Param("example") SpiderRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") SpiderRecord record, @Param("example") SpiderRecordExample example);

    int updateByExample(@Param("record") SpiderRecord record, @Param("example") SpiderRecordExample example);

    int updateByPrimaryKeySelective(SpiderRecord record);

    int updateByPrimaryKeyWithBLOBs(SpiderRecord record);

    int updateByPrimaryKey(SpiderRecord record);
}