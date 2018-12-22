package com.mall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.util.JsonResponse;
import com.mall.common.util.constant.Constant;
import com.mall.utils.ShiroUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 全局过滤器
 * @author King chen
 * @emai 396885563@qq.com
 * @date 2018年3月7日
 */
@WebFilter(filterName = "globalFilter", urlPatterns = "/*")
public class GlobalFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置跨域
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        res.setHeader("Access-Control-Max-Age", "0");
        res.setHeader("Access-Control-Allow-Headers",
                "Origin, No-Cache, X-Requested-With, X-Content-Type-Options, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("X-XSS-Protection", "1");
        res.setHeader("XDomainRequestAllowed", "1");
        String url = req.getRequestURI();
        //为第三方插件权限拦截、只允许超级管理员
        if(url.contains("monitoring") || url.contains("druid")){
            try {
                if(ShiroUtils.getUserId() != Constant.SUPER_ADMIN){
                    accessDenied(res);
                }
            } catch (Exception e) {
                logger.info("没有权限！");
                accessDenied(res);
            }
        }
     /* 退出登录跳过monitoring监控的过滤链防止再次获取session与shiro冲突*/
        if(url.contains("/sys/logout")){
            String servletPath = req.getServletPath();
            req.getRequestDispatcher(servletPath).forward(req, res);
        }else{
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("GlobalFilter init");
    }

    @Override
    public void destroy() {
        logger.info("GlobalFilter destroy");
    }


    /**
     * 拒绝访问处理
     */
    private void accessDenied(HttpServletResponse response) throws IOException, ServletException {
        logger.warn("权限不够,拒绝访问!");
        String json = new Gson().toJson(JsonResponse.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
        response.getOutputStream().print(json);
    }
}

