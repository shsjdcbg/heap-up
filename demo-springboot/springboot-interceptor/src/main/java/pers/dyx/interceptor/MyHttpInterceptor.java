package pers.dyx.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 拦截处理类
 *
 * @author Minbo.He
 */
@Component
public class MyHttpInterceptor extends HandlerInterceptorAdapter {

    protected static Logger logger = LoggerFactory.getLogger(MyHttpInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = "";
        // 去掉最后一个空格
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        queryString = queryString.equals("") ? null : queryString.substring(0, queryString.length() - 1);
        logger.info(String.format("请求参数, url: %s, method: %s, params: %s", url, method, queryString));

        // hello不做拦截
        if (uri.equals("/hello")) {
            return true;
        }

        // 其他拦截请求（请求必须都带上用户id）
        String userId = request.getParameter("userId");
        if (userId != null) {
            return true;

        } else {
            this.output(response, "{\n"
                    + "\"code\": \"4001\",\n"
                    + "\"message\": \"参数错误\"\n"
                    + "}");
            return false;
        }
    }

    /**
     * 输出结果
     */
    private void output(HttpServletResponse response, String result) throws Exception {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}
