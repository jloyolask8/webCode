package com.webcode.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility filter to add CORS Headers to requests.
 *
 * usage in web.xml
 *
 * <filter>
 * <filter-name>CorsFilter</filter-name>
 * <filter-class>com.webcode.filters.CorsFilter</filter-class>
 * <init-param>
 * <param-name>Access-Control-Allow-Origin</param-name>
 * <param-value>www.example.com</param-value>
 * </init-param>
 * <init-param>
 * <param-name>Access-Control-Allow-Credentials</param-name>
 * <param-value>true</param-value>
 * </init-param>
 * <init-param>
 * <param-name>Access-Control-Allow-Methods</param-name>
 * <param-value>GET, POST, DELETE, PUT</param-value>
 * </init-param>
 * <init-param>
 * <param-name>Access-Control-Allow-Headers</param-name>
 * <param-value>Content-Type, Accept, authorization, token</param-value>
 * </init-param>
 * </filter>
 * <filter-mapping>
 * <filter-name>CorsFilter</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 *
 * @author jonathan
 * TODO add logging
 */
public class CorsFilter implements Filter {

    private String accessControlAllowOrigin;
    private String accessControlAllowCredentials;
    private String accessControlAllowMethods;
    private String accessControlAllowHeaders;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
//        String path = ((HttpServletRequest) request).getRequestURI();

        if (!StringUtils.isEmpty(accessControlAllowOrigin)) {
            res.setHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
        }
        if (!StringUtils.isEmpty(accessControlAllowCredentials)) {
            res.setHeader("Access-Control-Allow-Credentials", accessControlAllowCredentials);
        }
        if (!StringUtils.isEmpty(accessControlAllowMethods)) {
            res.setHeader("Access-Control-Allow-Methods", accessControlAllowMethods);
        }
        if (!StringUtils.isEmpty(accessControlAllowHeaders)) {
            res.setHeader("Access-Control-Allow-Headers", accessControlAllowHeaders);
        }
        // IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for 
        // this case before validating the headers (CORS stuff)
        if (req.getMethod().equals("OPTIONS")) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, response);
    }

   

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        accessControlAllowOrigin = FilterCommons.readParameter("Access-Control-Allow-Origin", filterConfig);
        accessControlAllowCredentials = FilterCommons.readParameter("Access-Control-Allow-Credentials", filterConfig);
        accessControlAllowMethods = FilterCommons.readParameter("Access-Control-Allow-Methods", filterConfig);
        accessControlAllowHeaders = FilterCommons.readParameter("Access-Control-Allow-Headers", filterConfig);

    }

}
