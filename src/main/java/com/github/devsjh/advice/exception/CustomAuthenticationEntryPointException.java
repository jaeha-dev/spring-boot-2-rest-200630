package com.github.devsjh.advice.exception;

/**
 * @title  : 익셉션 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 * @memo   : 토큰 인증 실패 시 발생할 예외
 */
public class CustomAuthenticationEntryPointException extends RuntimeException {

    public CustomAuthenticationEntryPointException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomAuthenticationEntryPointException(String message) {
        super(message);
    }

    public CustomAuthenticationEntryPointException() {
        super();
    }
}