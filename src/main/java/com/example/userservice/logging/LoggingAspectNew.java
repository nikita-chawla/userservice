//package com.example.userservice.logging;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//@Aspect
//@Component
//public class LoggingAspectNew {
//
//    private static final Logger logger = LogManager.getLogger(LoggingAspectNew.class);
//    private final ObjectMapper objectMapper;
//
//    public LoggingAspectNew(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Around("execution(* com.example.userservice.controller.*.*(..))")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (attributes == null) {
//            return joinPoint.proceed();
//        }
//
//        HttpServletRequest request = attributes.getRequest();
//        HttpServletResponse response = attributes.getResponse();
//
//        if (!(request instanceof ContentCachingRequestWrapper) || !(response instanceof ContentCachingResponseWrapper)) {
//            return joinPoint.proceed();
//        }
//
//
//        ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
//
//        long startTime = System.currentTimeMillis();
//        Object result = joinPoint.proceed();
//        long timeTaken = System.currentTimeMillis() - startTime;
//        ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
//
//        logRequestDetails(requestWrapper);
//
//
//        // Ensure response is written to buffer before accessing body
//        responseWrapper.copyBodyToResponse();
//        // Flush the response buffer to ensure data is written before logging
//        ((HttpServletResponse) response).flushBuffer();
//        // Asynchronous Listener for Response Completion
////        AsyncListener listener = new AsyncListener() {
////            @Override
////            public void onComplete(AsyncEvent event) throws IOException {
////                logResponseDetails(responseWrapper, timeTaken);
////            }
////
////            @Override
////            public void onTimeout(AsyncEvent event) throws IOException {
////                logger.warn("Response write timed out");
////            }
////
////            @Override
////            public void onError(AsyncEvent event) throws IOException {
////                logger.error("Error writing response", event.getThrowable());
////            }
////
////            @Override
////            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
////
////            }
////        };
////
////        ((AsyncContext) response).addListener(listener);
//        logResponseDetails(responseWrapper, timeTaken, result);
//        return result;
//    }
//
//    private void logRequestDetails(ContentCachingRequestWrapper request) {
//        String requestBody = getRequestBody(request);
//        logger.info("Request URI: {}", request.getRequestURI());
//        logger.info("Request Method: {}", request.getMethod());
//        logger.info("Request Headers: {}", getHeaders(request));
//        logger.info("Request Body: {}", requestBody);
//    }
//
//    private void logResponseDetails(ContentCachingResponseWrapper response, long timeTaken, Object result) throws IOException {
//        if (response == null) {
//            logger.warn("ContentCachingResponseWrapper is null");
//            return;
//        }
//
//        String responseBody = null;
//        try {
//            response.copyBodyToResponse();
//            responseBody = getResponseBody(response);
//        } catch (IOException ex) {
//            logger.error("Error reading response body", ex);
//        }
//
//        logger.info("Response Status: {}", response.getStatus());
//        logger.info("Response Headers: {}", getHeaders(response));
//        logger.info("Response Body: {}", convertObjectToJson(result));
//        logger.info("Response Time Taken: {} ms", timeTaken);
//    }
//    private String convertObjectToJson(Object object) {
//        try {
//            return objectMapper.writeValueAsString(object);
//        } catch (Exception e) {
//            logger.error("Error converting object to JSON", e);
//            return "";
//        }
//    }
//
//    private String getRequestBody(ContentCachingRequestWrapper request) {
//        byte[] buf = request.getContentAsByteArray();
//        if (buf.length > 0) {
//            try {
//                return new String(buf, 0, buf.length, request.getCharacterEncoding());
//            } catch (UnsupportedEncodingException ex) {
//                return "[Unsupported Encoding]";
//            }
//        }
//        return "";
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
//    private String getHeaders(HttpServletRequest request) {
//        StringBuilder headers = new StringBuilder();
//        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
//            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
//        });
//        return headers.toString();
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
