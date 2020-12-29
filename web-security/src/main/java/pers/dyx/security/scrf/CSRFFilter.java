package pers.dyx.security.scrf;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dyx
 * @date 2019/7/19 15:42
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "CSRFFilter")
public class CSRFFilter implements Filter {
    private static String BlankList = null;

    public CSRFFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String origin = request.getHeader("origin");
        String referer = request.getHeader("referer");
        String method = request.getMethod();
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!"GET".equals(method) && !"POST".equals(method) && !"HEAD".equals(method) && !"PUT".equals(method)) {
            response.setContentType("text/html;charset=GBK");
            response.setCharacterEncoding("GBK");
            response.setStatus(403);
            response.getWriter().write("<font size=6 color=red>对不起，您的请求非法，系统拒绝响应!</font>");
            return;
        }

        if (StringUtils.isNotBlank(referer)) {
            referer = this.getHostAddr(referer);
            if (!BlankList.contains(referer)) {
                servletResponse.setContentType("text/html; charset=utf-8");
                servletResponse.getWriter().write("<script language='javascript'>alert('请求存在风险!');history.go(-1);</script>");
                return;
            }
        } else if (StringUtils.isNotBlank(origin)) {
            origin = this.getHostAddr(origin);
            if (!BlankList.contains(origin)) {
                servletResponse.setContentType("text/html; charset=utf-8");
                servletResponse.getWriter().write("<script language='javascript'>alert('请求存在风险!');history.go(-1);</script>");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        BlankList = ConfigUtil.BLANK_LIST;
    }

    private String getHostAddr(String host) {
        if (host.startsWith("http")) {
            host = host.substring(host.indexOf("://") + 3);
        }

        if (host.contains("/")) {
            host = host.substring(0, host.indexOf("/"));
        }

        if (host.contains(":")) {
            host = host.substring(0, host.indexOf(":"));
        }

        return host;
    }
}
