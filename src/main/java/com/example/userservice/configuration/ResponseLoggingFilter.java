//package com.example.userservice.configuration;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//@Component
//public class ResponseLoggingFilter extends OncePerRequestFilter {
//
//    private static final Logger logger = LogManager.getLogger(ResponseLoggingFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
//
//        filterChain.doFilter(request, responseWrapper);
//
//        String requestPath = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
//        int statusCode = responseWrapper.getStatus();
//
//        responseWrapper.copyBodyToResponse();
//        String responseBody = getResponseBody(responseWrapper);
//
//        logger.info("Request Path: {}", requestPath);
//        logger.info("Response Status: {}", statusCode);
//        logger.info("Response Body: {}", responseBody);
//    }
//
//    private String getResponseBody(ContentCachingResponseWrapper response) {
//        byte[] buf = response.getContentAsByteArray();
//        if (buf.length > 0) {
//            try {
//                return new String(buf, 0, buf.length, response.getCharacterEncoding());
//            } catch (UnsupportedEncodingException ex) {
//                return "[Unsupported Encoding]";
//            }
//        }
//        return "";
//    }
//
//
//}
