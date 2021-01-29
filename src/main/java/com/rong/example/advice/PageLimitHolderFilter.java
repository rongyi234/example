package com.rong.example.advice;

import com.rong.example.bean.bo.PageLimit;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 分页参数过滤
 */
public class PageLimitHolderFilter implements Filter {
    /**
     * 每个过滤器拦截器，都设置自己的线程上下文，互不干扰
     */
    private static final ThreadLocal<PageLimit> threadLocal = new ThreadLocal();

    public PageLimitHolderFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        threadLocal.set(new PageLimit((HttpServletRequest) request));
        chain.doFilter(request, response);

    }

    public static PageLimit getContext() {
        return (PageLimit) threadLocal.get();
    }

    public static void setContext(PageLimit pageLimit) {
        threadLocal.set(pageLimit);
    }

}
