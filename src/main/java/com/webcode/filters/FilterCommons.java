package com.webcode.filters;

import javax.servlet.FilterConfig;

/**
 * Utils methods available to all filters
 *
 * @author jonathan
 */
public class FilterCommons {

    protected static String readParameter(String parameter, FilterConfig config) {
        String first = config.getInitParameter(parameter);
        if (hasValue(first)) {
            return first;
        }
        final String second = config.getServletContext().getInitParameter(
                parameter);
        if (hasValue(second)) {
            return second;
        }
        return null;
//        throw new IllegalArgumentException(parameter + " needs to be defined");
    }

    protected static boolean hasValue(String value) {
        return value != null && value.trim().length() > 0;
    }

}
