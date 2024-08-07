package com.example.userservice.logging;////package com.example.userservice.logging;
////
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import org.aspectj.lang.ProceedingJoinPoint;
////import org.aspectj.lang.annotation.Around;
////import org.aspectj.lang.annotation.Aspect;
////import org.springframework.stereotype.Component;
////import org.springframework.web.context.request.RequestContextHolder;
////import org.springframework.web.context.request.ServletRequestAttributes;
////import org.springframework.web.util.ContentCachingRequestWrapper;
////import org.springframework.web.util.ContentCachingResponseWrapper;
////
////import java.io.UnsupportedEncodingException;
////
////@Aspect
////@Component
////public class LoggingAspect {
////
////    @Around("execution(* com.example.userservice.controller.*.*(..))")
////    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
////        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
////        HttpServletRequest request = attributes.getRequest();
////        HttpServletResponse response = attributes.getResponse();
////
////        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
////        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
////
////        long startTime = System.currentTimeMillis();
////        Object result = joinPoint.proceed();
////        long timeTaken = System.currentTimeMillis() - startTime;
////
////        logRequestDetails(requestWrapper);
////        logResponseDetails(responseWrapper, timeTaken);
////
////        responseWrapper.copyBodyToResponse();
////
////        return result;
////    }
////
////    private void logRequestDetails(ContentCachingRequestWrapper request) {
////        String requestBody = getRequestBody(request);
////        System.out.println("Request URI: " + request.getRequestURI());
////        System.out.println("Request Body: " + requestBody); // Ensure this prints correctly
////    }
////
////    private void logResponseDetails(ContentCachingResponseWrapper response, long timeTaken) {
////        String responseBody = getResponseBody(response);
////        System.out.println("Response Status: " + response.getStatus());
////        System.out.println("Response Body: " + responseBody); // Ensure this prints correctly
////        System.out.println("Response Time Taken: " + timeTaken + "ms");
////    }
////
////    private String getRequestBody(ContentCachingRequestWrapper request) {
////        byte[] buf = request.getContentAsByteArray();
////        if (buf.length > 0) {
////            try {
////                return new String(buf, 0, buf.length, request.getCharacterEncoding());
////            } catch (UnsupportedEncodingException ex) {
////                return "[Unsupported Encoding]";
////            }
////        }
////        return "";
////    }
////
////    private String getResponseBody(ContentCachingResponseWrapper response) {
////        byte[] buf = response.getContentAsByteArray();
////        if (buf.length > 0) {
////            try {
////                return new String(buf, 0, buf.length, response.getCharacterEncoding());
////            } catch (UnsupportedEncodingException ex) {
////                return "[Unsupported Encoding]";
////            }
////        }
////        return "";
////    }
////}
//package com.example.userservice.logging;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.After;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//@Aspect
//@Component
//public class LoggingAspect {
//    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
//
//    @Pointcut("execution(* com.example..*.*(..))")
//    public void applicationPackagePointcut() {
//        // Method is empty as this is just a Pointcut, the implementations are in the advices.
//    }
//
//    @Before("applicationPackagePointcut() && args(.., @RequestBody body)")
//    public void logRequestBody(JoinPoint joinPoint, Object body) {
//        // Log the request body
//        System.out.println("Request Body: " + body);
//    }
//
//    @Before("applicationPackagePointcut() && args(.., @RequestHeader headers)")
//    public void logRequestHeaders(JoinPoint joinPoint, Object headers) {
//        // Log the request headers
//        System.out.println("Request Headers: " + headers);
//    }
//
//    @Around("applicationPackagePointcut()")
//    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//
//        for (Object arg : joinPoint.getArgs()) {
//            if (arg instanceof HttpServletRequest) {
//                request = (HttpServletRequest) arg;
//            } else if (arg instanceof HttpServletResponse) {
//                response = (HttpServletResponse) arg;
//            }
//        }
//
//        if (request != null && response != null) {
//            ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
//            ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
//
//            // Proceed with the next advice or target method invocation
//            Object result = joinPoint.proceed();
//
//            // Log request details
//            logRequestDetails(wrappedRequest);
//
//            // Log response details
//            logResponseDetails(wrappedResponse);
//
//            return result;
//        } else {
//            return joinPoint.proceed();
//        }
//    }
//
////    private void logRequestDetails(ContentCachingRequestWrapper request) throws IOException {
////        String requestBody = new String(request.getContentAsByteArray());
////        System.out.println("Request URI: " + request.getRequestURI());
////        System.out.println("Request Headers: " + request.getHeaderNames());
////        System.out.println("Request Body: " + requestBody);
////    }
////
////    private void logResponseDetails(ContentCachingResponseWrapper response) throws IOException {
////        String responseBody = new String(response.getContentAsByteArray());
////        System.out.println("Response Headers: " + response.getHeaderNames());
////        System.out.println("Response Body: " + responseBody);
////        response.copyBodyToResponse();
////    }
//        private void logRequestDetails(ContentCachingRequestWrapper request) {
//        String requestBody = getRequestBody(request);
//        logger.info("Request URI: {}", request.getRequestURI());
//        logger.info("Request Method: {}", request.getMethod());
//        logger.info("Request Headers: {}", getHeaders(request));
//        logger.info("Request Body: {}", requestBody);
//    }
//
//    private void logResponseDetails(ContentCachingResponseWrapper response) throws IOException {
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
//        logger.info("Response Body: {}", responseBody);
////        logger.info("Response Time Taken: {} ms", timeTaken);
//    }
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
//
//}
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//public class LoggingAspect {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
//    @Around("execution(* *.*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
//    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
//        // Get the method arguments
//        Object[] args = joinPoint.getArgs();
//
//        // Find the @RequestBody parameter if it exists
//        Object requestBody = findRequestBody(args);
//
//        // Get method signature for method details
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String methodName = methodSignature.getMethod().getName();
//
//        // Get request URI
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String requestURI = request.getRequestURI();
//
//        System.out.println("Method: " + methodName);
//        System.out.println("URI: " + requestURI);
//        System.out.println("Request Body: " + requestBody);
//        Object result = joinPoint.proceed();
//        System.out.println("Response Body: " + result);
//
//        return result;
//    }
//
//    // Method to find the @RequestBody parameter in the method arguments
//    private Object findRequestBody(Object[] args) {
//        for (Object arg : args) {
//            return arg; // Assuming the request body is a String in this case
//        }
//        return null;
//    }
//}