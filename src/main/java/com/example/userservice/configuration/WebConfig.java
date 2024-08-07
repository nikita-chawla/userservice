//package com.example.userservice.configuration;
//
//import com.example.userservice.configuration.RequestResponseLoggingFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.logging.Filter;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
//        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new RequestResponseLoggingFilter());
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
////    @Bean
////    public Filter responseLoggingFilter() {
////        return (Filter) new ResponseLoggingFilter();
////    }
//}
