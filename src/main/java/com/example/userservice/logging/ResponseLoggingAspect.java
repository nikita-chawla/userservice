//package com.example.userservice.logging;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//@Aspect
//@Component
//public class ResponseLoggingAspect {
//
//    private static final Logger logger = LogManager.getLogger(ResponseLoggingAspect.class);
//
//    @AfterReturning(pointcut = "execution(* com.example.userservice.controller.*.*(..))", returning = "retVal")
//    public void logResponse(JoinPoint joinPoint, Object retVal) throws Throwable {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = attributes.getResponse();
//
//        if (!(response instanceof ContentCachingResponseWrapper)) {
//            return;
//        }
//
//        ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
//        long startTime = System.currentTimeMillis(); // Assuming start time is captured elsewhere
//
//        // Ensure data is written before logging (optional, might already be flushed)
//        responseWrapper.copyBodyToResponse();
//
//        long timeTaken = startTime - System.currentTimeMillis();
//        logResponseDetails(responseWrapper, timeTaken);
//    }
//
//    private void logResponseDetails(ContentCachingResponseWrapper response, long timeTaken) throws IOException {
//        String responseBody = getResponseBody(response);
//        logger.info("Response Status: {}", response.getStatus());
//        logger.info("Response Headers: {}", getHeaders(response));
//        logger.info("Response Body: {}", responseBody);
//        logger.info("Response Time Taken: {} ms", timeTaken);
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
//    private String getHeaders(HttpServletResponse response) {
//        StringBuilder headers = new StringBuilder();
//        response.getHeaderNames().forEach(headerName -> {
//            headers.append(headerName).append(": ").append(response.getHeader(headerName)).append("\n");
//        });
//        return headers.toString();
//    }
//}
