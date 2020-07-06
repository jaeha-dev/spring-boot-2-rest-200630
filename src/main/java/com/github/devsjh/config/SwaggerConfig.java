package com.github.devsjh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @title  : Swagger 설정 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                                                      // API 문서 패키지 지정 (controller 패키지의 v1 패키지만)
                                                      .apis(RequestHandlerSelectors.basePackage("com.github.devsjh.controller"))
                                                      .paths(PathSelectors.ant("/v1/**"))
                                                      // .paths(PathSelectors.any())
                                                      .build()
                                                      .useDefaultResponseMessages(false);
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation")
                                   .description("API 문서")
                                   .license("jaeha-dev")
                                   .licenseUrl("https://github.com/jaeha-dev")
                                   .version("1")
                                   .build();
    }
}