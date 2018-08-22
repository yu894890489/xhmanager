package com.xh.comm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 放开跨域
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO  用户登陆信息的检查
 */
public class RequestFilter implements Filter{
	private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		HttpServletResponse httpResponse =(HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");//开启跨域
		log.info(httpRequest.getRemoteHost());
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
