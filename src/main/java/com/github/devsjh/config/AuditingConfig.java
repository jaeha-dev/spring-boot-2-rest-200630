package com.github.devsjh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @title  : JPA 감사 설정 클래스
 * @author : jaeha-dev (Git)
 * @memo   : 엔터티에 시각 정보를 담기 위해 정의한다.
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {
}