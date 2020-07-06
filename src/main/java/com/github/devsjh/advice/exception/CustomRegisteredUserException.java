package com.github.devsjh.advice.exception;

/**
 * @title  : 익셉션 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 * @memo   : 등록된 계정으로 계정 등록 시 발생할 예외
 */
public class CustomRegisteredUserException extends RuntimeException {

    public CustomRegisteredUserException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomRegisteredUserException(String message) {
        super(message);
    }

    public CustomRegisteredUserException() {
        super();
    }
}
