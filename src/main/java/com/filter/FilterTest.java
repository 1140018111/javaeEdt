package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * com.filter
 * dxl
 *
 * @author DL
 * @create 2020-06-08
 */
@WebFilter(filterName = "用户拦截器",urlPatterns = "/query/b")
public class FilterTest implements javax.servlet.Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("拦截器执行方法。");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("拦截器初始化方法");
	}

	@Override
	public void destroy() {
		System.out.println("过滤销毁方法");
	}
}
