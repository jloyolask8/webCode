/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcode.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * Character encoding filter utility
 * 
 * usage in web.xml:
 * <filter>
 * <filter-name>CharacterEncodingFilter</filter-name>
 * <filter-class>com.webcode.filters.CharacterEncodingFilter</filter-class>
 * <init-param>
 * <param-name>characterEncoding</param-name>
 * <param-value>UTF-8</param-value>
 * </init-param>
 * </filter>
 * <filter-mapping>
 * <filter-name>CharacterEncodingFilter</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 *
 * @author jonathan
 */
public class CharacterEncodingFilter implements Filter {

    private String characterEncoding;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            if (!StringUtils.isEmpty(characterEncoding)) {
                req.setCharacterEncoding(characterEncoding);
            }
        } catch (Exception e) {
            System.out.println("Error on setCharacterEncoding for request : " + e.getMessage());
        }

        try {
            if (!StringUtils.isEmpty(characterEncoding)) {
                resp.setCharacterEncoding(characterEncoding);
            }
        } catch (Exception e) {
            System.out.println("Error on setCharacterEncoding for response : " + e.getMessage());
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        characterEncoding = FilterCommons.readParameter("characterEncoding", filterConfig);

    }

    @Override
    public void destroy() {
    }
}
