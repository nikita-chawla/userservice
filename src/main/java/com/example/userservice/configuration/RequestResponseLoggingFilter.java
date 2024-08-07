//package com.example.userservice.configuration;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/*")
//public class RequestResponseLoggingFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpRequest);
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);
//
//        try {
//            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(requestWrapper, responseWrapper));
//            chain.doFilter(requestWrapper, responseWrapper);
//        } finally {
//            responseWrapper.copyBodyToResponse();
//            RequestContextHolder.resetRequestAttributes();
//        }
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // No initialization required
//    }
//
//    @Override
//    public void destroy() {
//        // No cleanup required
//    }
//}
