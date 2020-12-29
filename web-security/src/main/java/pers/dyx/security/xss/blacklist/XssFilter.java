package pers.dyx.security.xss.blacklist;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Description：XSS 过滤器
 *
 * @author dyx
 * @date 2019/11/1 13:08
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = null;
        if(request instanceof HttpServletRequest){
            xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        }
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
    }

}