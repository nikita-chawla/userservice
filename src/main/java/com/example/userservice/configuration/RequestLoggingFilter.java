//package com.example.userservice.configuration;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//
//@Component
//public class RequestLoggingFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization code if needed
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//
//        long startTime = System.currentTimeMillis();
//        try {
//            filterChain.doFilter(requestWrapper, responseWrapper);
//        } finally {
//            logRequestAndResponse(requestWrapper, responseWrapper, startTime);
//            responseWrapper.copyBodyToResponse();
//        }
//    }
//
//    private void logRequestAndResponse(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, long startTime) {
//        // Log request details
//        String requestBody = new String(request.getContentAsByteArray());
//        System.out.println("Request URI: " + request.getRequestURI());
//        System.out.println("Request Body: " + requestBody);
//
//        // Log response details
//        String responseBody = new String(response.getContentAsByteArray());
//        long timeTaken = System.currentTimeMillis() - startTime;
//        System.out.println("Response Status: " + response.getStatus());
//        System.out.println("Response Body: " + responseBody);
//        System.out.println("Response Time Taken: " + timeTaken + "ms");
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup code if needed
//    }
//}
