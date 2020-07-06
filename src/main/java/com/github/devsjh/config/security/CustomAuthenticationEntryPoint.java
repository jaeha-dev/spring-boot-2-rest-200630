package com.github.devsjh.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title  : 인증 엔트리 포인트 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override // 인증과 관련한 문제가 발생할 경우 포워딩한다.
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        response.sendRedirect("/error/entry-point");
    }
}