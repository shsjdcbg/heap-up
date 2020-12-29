package pers.dyx.security.xss.blacklist;

import org.owasp.esapi.ESAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

/**
 * Description：敏感字符转换类
 *
 * @author dyx
 * @date 2019/11/1 13:10
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    private static final Pattern SCRIPT_PATTERN =
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
    private static final Pattern SRC_PATTERN =
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern SRC_PATTERN_V2 =
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern SINGLE_SCRIPT_PATTERN = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
    private static final Pattern SINGLE_SCRIPT_PATTERN_V2 =
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern EVAL_PATTERN =
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern EXPRESSION_PATTERN =
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern JAVASCRIPT_PATTERN = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
    private static final Pattern VBSCRIPT_PATTERN = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
    private static final Pattern ONLOAD_PATTERN =
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern ON_PATTERN =
            Pattern.compile("on.*(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    private String cleanXSS(String value) {
        if (value != null) {
            // 推荐使用ESAPI库来避免脚本攻击
            value = ESAPI.encoder().canonicalize(value);
            // 避免空字符串
            value = value.replaceAll("", "");
            // 避免script 标签
            value = SCRIPT_PATTERN.matcher(value).replaceAll("");
            // 避免src形式的表达式
            value = SRC_PATTERN.matcher(value).replaceAll("");
            value = SRC_PATTERN_V2.matcher(value).replaceAll("");
            // 删除单个的 </script> 标签
            value = SINGLE_SCRIPT_PATTERN.matcher(value).replaceAll("");
            // 删除单个的<script ...> 标签
            value = SINGLE_SCRIPT_PATTERN_V2.matcher(value).replaceAll("");
            // 避免 eval(...) 形式表达式
            value = EVAL_PATTERN.matcher(value).replaceAll("");
            // 避免 expression(...) 表达式
            value = EXPRESSION_PATTERN.matcher(value).replaceAll("");
            // 避免 javascript: 表达式
            value = JAVASCRIPT_PATTERN.matcher(value).replaceAll("");
            // 避免 vbscript: 表达式
            value = VBSCRIPT_PATTERN.matcher(value).replaceAll("");
            // 避免 οnlοad= 表达式
            value = ONLOAD_PATTERN.matcher(value).replaceAll("");
            // 避免 onXX= 表达式
            value = ON_PATTERN.matcher(value).replaceAll("");
        }
        return value;
    }
}

