package pers.dyx;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * 普通Web接口测试
 *
 * @author dyx
 * @date 2020/10/30 11:01
 */
@RunWith(SpringRunner.class)
// 指定启动类和随机端口
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationWebApiTest {

    protected static Logger logger = LoggerFactory.getLogger(ApplicationWebApiTest.class);

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        logger.info("随机自动分配端口后的整体请求头--->>> " + url);
        this.base = new URL(url);
    }

    /**
     * 向"/hello"地址发送请求，并打印返回结果
     *
     * @throws Exception
     */
    @Test
    public void testUrlApi() throws Exception {
        String url = this.base.toString() + "/test";
        logger.info("待测试接口地址：url=" + url);
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class, "");
        String result = response.getBody();
        logger.info("返回结果：result=" + result);
        boolean expected = false;
        if (result != null && result.contains("Hello")) {
            expected = true;
        }
        TestCase.assertEquals(true, expected);
    }
}
