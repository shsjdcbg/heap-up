package pers.dyx;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 普通业务服务层测试
 *
 * @author dyx
 * @date 2020/10/30 11:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class) // 指定启动类
public class ApplicationWebServiceTest {

    protected static Logger logger = LoggerFactory.getLogger(ApplicationWebServiceTest.class);

    @Autowired
    private DemoService demoService;

    /**
     * 测试方法
     *
     * @throws Exception
     */
    @Test
    public void testService() throws Exception {
        // 检查入口配置是否正确
        String result = this.demoService.sayHello();
        logger.info("实际返回结果result=" + result);

        TestCase.assertNotNull(result);
        String expected = "hello from service layer";
        TestCase.assertEquals(expected, result);
    }
}
