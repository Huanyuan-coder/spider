package mobi.huanyuan.spider;

import mobi.huanyuan.spider.bean.TestInfo;
import mobi.huanyuan.spider.mapper.TestInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpiderApplicationTests {

    @Autowired
    private TestInfoMapper testInfoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testDao() {
        TestInfo testInfo2 = new TestInfo();
        testInfo2.setCreateTime(new Date());
        testInfoMapper.insert(testInfo2);
    }
}
