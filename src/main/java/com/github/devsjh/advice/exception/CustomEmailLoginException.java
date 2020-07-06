package com.github.devsjh.advice.exception;

/**
 * @title  : 익셉션 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 * @memo   : 계정 로그인 실패 시 발생할 예외
 */
public class CustomEmailLoginException extends RuntimeException {

    public CustomEmailLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomEmailLoginException(String message) {
        super(message);
    }

    public CustomEmailLoginException() {
        super();
    }
}