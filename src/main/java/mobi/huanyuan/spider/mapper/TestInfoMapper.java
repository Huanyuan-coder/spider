package mobi.huanyuan.spider.mapper;

import java.util.List;
import mobi.huanyuan.spider.bean.TestInfo;
import mobi.huanyuan.spider.bean.TestInfoExample;
import org.apache.ibatis.annotations.Param;

public interface TestInfoMapper {
    long countByExample(TestInfoExample example);

    int deleteByExample(TestInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestInfo record);

    int insertSelective(TestInfo record);

    List<TestInfo> selectByExample(TestInfoExample example);

    TestInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestInfo record, @Param("example") TestInfoExample example);

    int updateByExample(@Param("record") TestInfo record, @Param("example") TestInfoExample example);

    int updateByPrimaryKeySelective(TestInfo record);

    int updateByPrimaryKey(TestInfo record);
}