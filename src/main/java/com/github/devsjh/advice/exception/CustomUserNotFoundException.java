package com.github.devsjh.advice.exception;

/**
 * @title  : 익셉션 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 * @memo   : 계정 조회 실패 시 발생할 예외
 */
public class CustomUserNotFoundException extends RuntimeException {

    public CustomUserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomUserNotFoundException(String message) {
        super(message);
    }

    public CustomUserNotFoundException() {
        super();
    }
}