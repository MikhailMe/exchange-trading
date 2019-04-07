package com.kspt.exchangetrading.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    private static final String MAX_AGE = "3600";
    private static final String ALLOW_ORIGIN = "*";
    private static final String ALLOW_HEADERS = "x-requested-with";
    private static final String ALLOW_METHODS = "HEAD, GET, POST, PUT, OPTIONS, DELETE";

    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ALLOW_ORIGIN);
        httpServletResponse.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOW_METHODS);
        httpServletResponse.setHeader(ACCESS_CONTROL_MAX_AGE, MAX_AGE);
        httpServletResponse.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, ALLOW_HEADERS);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
