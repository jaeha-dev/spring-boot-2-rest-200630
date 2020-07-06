package com.github.devsjh.config;

import com.github.devsjh.config.security.CustomAccessDeniedHandler;
import com.github.devsjh.config.security.CustomAuthenticationEntryPoint;
import com.github.devsjh.config.security.JwtAuthenticationFilter;
import com.github.devsjh.config.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @title  : 보안 설정 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    @Bean // 비밀번호 해싱
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // REST API이므로 비활성화 (활성화 시, 로그인 폼 화면으로 리다이렉트된다.)
            .httpBasic().disable()
            // REST API이므로 CSRF 보안 비활성화
            .csrf().disable()
            // JWT 인증 방식을 사용하므로 세션을 유지하지 않는다. (REST API 기본 원칙)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                // 다음 요청에 대한 접근 권한 검사
                .authorizeRequests()
                    // 계정 등록 및 로그인 URL은 모두에게 접근 허용
                    .antMatchers("/", "/*/join", "/*/login").permitAll()
                    // error, hello로 시작하는 리소스에 대한 GET 요청은 모두에게 접근 허용
                    .antMatchers(HttpMethod.GET, "/error/**", "/hello/**").permitAll()
                    // 모든 계정 조회는 ADMIN 권한 요구
                    // .antMatchers("/*/users").hasRole("ADMIN")
                    // 상기 URL 외의 나머지 접근은 USER 권한 요구
                    .anyRequest().hasRole("USER")
            .and()
                // 접근 불가 핸들러 추가
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
            .and()
                // 인증 엔트리 포인트 추가
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
            .and()
                // JWT 인증 필터를 ID/PW 필터의 이전 단계에 추가한다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override // Swagger 리소스에 대한 검사를 무시한다.
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger/**");
    }
}