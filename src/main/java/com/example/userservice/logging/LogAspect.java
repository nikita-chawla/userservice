package com.example.userservice.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LogManager.getLogger(LogAspect.class);
    private final ObjectMapper objectMapper;

    public LogAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Around("execution(* *.*(..)) && @within(org.springframework.web.bind.annotation.RestController)")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // Capture request details
        String method = request.getMethod();
        String requestURI = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        // Collect headers
        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map(headerName -> "-H \"" + headerName + ": " + Collections.list(request.getHeaders(headerName)).stream().collect(Collectors.joining(",")) + "\"")
                .collect(Collectors.joining(" "));

        // Convert method arguments to JSON (assuming the request body is one of the arguments)
        String body = Arrays.stream(joinPoint.getArgs())
                .map(arg -> {
                    try {
                        String jsonString = objectMapper.writeValueAsString(arg);
                        return StringEscapeUtils.escapeJson(jsonString);
                    } catch (Exception e) {
                        return arg.toString();
                    }
                })
                .collect(Collectors.joining(" "));

        // Construct the cURL command
        String curlCommand = "curl -X " + method + " \"" + requestURI + (queryString != null ? "?" + queryString : "") + "\" " + headers + " -d \"" + body + "\"";

        // Log the cURL command
        System.out.println("Generated cURL Command: " + curlCommand);
        logger.info("Generated cURL Command: {}", curlCommand);

        // Proceed with the original method execution
        Object result = joinPoint.proceed();

        // Log response details
        System.out.println("Response: " + objectMapper.writeValueAsString(result));
        logger.info("Response: {}", objectMapper.writeValueAsString(result));

        return result;
    }
}