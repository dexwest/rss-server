package com.hosmerlake.rss.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * When TRACE logging is enabled, this interceptor will log the execution
 * time of handling the incoming requests.
 */
@Component("metric-logger-interceptor")
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="request")
public class MetricLoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(MetricLoggerInterceptor.class);

    private long start;
    private String url;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(logger.isTraceEnabled()) {
            start = System.currentTimeMillis();
            url = request.getRequestURL().toString() + "?" + request.getQueryString();
            logger.trace("begin handling the request " + url);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(logger.isTraceEnabled()) {
            request.getSession().getId();
            long now = System.currentTimeMillis();
            logger.trace("[" + (now-start) + "ms] completed request " + url );
        }
    }
}
