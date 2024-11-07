//package com.primus.ripple.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**") // Allow CORS on all endpoints
//				.allowedOrigins("http://127.0.0.1:5500") // Allow requests from this origin
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specified methods
//				.allowedHeaders("*") // Allow all headers
//				.allowCredentials(true); // Allow credentials (cookies, authorization headers)
//	}
//}
