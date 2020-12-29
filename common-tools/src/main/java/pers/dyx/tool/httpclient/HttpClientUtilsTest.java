package pers.dyx.tool.httpclient;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: HttpClientUtils工具类测试
 *
 * @author dyx
 * @date Created on 2018年4月19日
 */
public class HttpClientUtilsTest {

    /**
     * Description: 测试get无参请求
     *
     * @throws Exception
     */
    public void testGet() throws Exception {
        HttpClientResult result = HttpClientUtils.doGet("http://127.0.0.1:8080/hello/get");
        System.out.println(result);
    }

    /**
     * Description: 测试get带参请求
     *
     * @throws Exception
     */
    public void testGetWithParam() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "download");
        params.put("path", "/etc/passwd");
        params.put("name", "%E5%B1%B1%E4%B8%9C%E6%94%BF%E5%8A%A1%E6%9C%8D%E5%8A%A1%E7%BD%91%E4%B8%AD%E4%BB%8B%E8%B6%85%E5%B8%82%E7%94%A8%E6%88%B7%E4%BD%BF%E7%94%A8%E6%89%8B%E5%86%8C.doc");
        HttpClientResult result = HttpClientUtils.doGet("http://221.214.94.36:5280/zjcs/shandong/DownLoad?action=download&path=useHelpDoc.doc&name=%25E5%25B1%25B1%25E4%25B8%259C%25E6%2594%25BF%25E5%258A%25A1%25E6%259C%258D%25E5%258A%25A1%25E7%25BD%2591%25E4%25B8%25AD%25E4%25BB%258B%25E8%25B6%2585%25E5%25B8%2582%25E7%2594%25A8%25E6%2588%25B7%25E4%25BD%25BF%25E7%2594%25A8%25E6%2589%258B%25E5%2586%258C.doc", params);
        System.out.println(result);
    }

    /**
     * Description: 测试post带请求头不带请求参数
     *
     * @throws Exception
     */
    public void testPost() throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Cookie", "123");
        headers.put("Connection", "keep-alive");
        headers.put("Accept", "application/json");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        HttpClientResult result = HttpClientUtils.doPost("http://127.0.0.1:8080/hello/post", headers, null);
        System.out.println(result);
    }

    public void testPostWithParam() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("date", "2019-06-25");
        params.put("day", "1");
        HttpClientResult result = HttpClientUtils.doPost("http://127.0.0.1:8088/main/imng/getWorkDate", params);
        System.out.println(result);
    }

    /**
     * Description: 测试post带参请求
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        HttpClientUtilsTest test = new HttpClientUtilsTest();
        test.testPostWithParam();
    }


}
