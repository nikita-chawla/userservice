//package com.example.userservice.configuration;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
//        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new RequestLoggingFilter());
//        registrationBean.addUrlPatterns("/api/*");  // URL patterns to filter
//        registrationBean.setName("RequestLoggingFilter");
//        registrationBean.setOrder(1);  // Filter order
//
//        return registrationBean;
//    }
//}
//
