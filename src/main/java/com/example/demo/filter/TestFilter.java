package com.example.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.LogRecord;

@Component
@WebFilter(urlPatterns = "/Blogs",filterName = "blosTest")
public class TestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
