package com.github.devsjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title  : Spring Boot 2로 REST API 만들기 실습 프로젝트
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 * @memo   : http://localhost:8000/swagger-ui.html (API 문서)
 */
@SpringBootApplication
public class DevsjhApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevsjhApplication.class, args);
    }

    // https://kauth.kakao.com/oauth/authorize?client_id=e37542e304ec631fa85bd3a0a1101a27&redirect_uri=http://localhost:8080/social/login/kakao&response_type=code
}