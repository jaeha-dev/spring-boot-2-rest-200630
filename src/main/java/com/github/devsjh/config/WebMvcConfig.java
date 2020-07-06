package com.github.devsjh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @title  : 웹 MVC 설정 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}