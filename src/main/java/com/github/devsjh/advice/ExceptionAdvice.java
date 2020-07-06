package com.github.devsjh.advice;

import com.github.devsjh.advice.exception.*;
import com.github.devsjh.model.response.CommonResult;
import com.github.devsjh.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

/**
 * @title  : 컨트롤러 예외처리 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 * @memo   : 공통으로 예외 처리를 적용할 패키지(controller)를 지정하였다.
 */
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.github.devsjh.controller")
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    // Exception.class(최상위 예외처리 클래스)를 지정하였으므로
    // 다른 ExceptionHandler에서 걸러지지 않은 익셉션이 있을 경우 해당 메소드를 거치도록 한다.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception exception) {
        return responseService.getFailureResult(Integer.parseInt(getMessage("unknown.code")),
                                                                 getMessage("unknown.message"));
    }

    // 존재하지 않는 계정 익셉션
    @ExceptionHandler(CustomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CustomUserNotFoundException exception) {
        return responseService.getFailureResult(Integer.parseInt(getMessage("userNotFound.code")),
                                                                 getMessage("userNotFound.message"));
    }

    // 이메일/비밀번호 불일치 익셉션
    @ExceptionHandler(CustomEmailLoginException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailLoginException(HttpServletRequest request, CustomEmailLoginException exception) {
        return responseService.getFailureResult(Integer.parseInt(getMessage("emailLoginFailed.code")),
                                                                 getMessage("emailLoginFailed.message"));
    }

    // 토큰 인증 실패 익셉션
    @ExceptionHandler(CustomAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CustomAuthenticationEntryPointException exception) {
        return responseService.getFailureResult(Integer.parseInt(getMessage("entryPointException.code")),
                                                                 getMessage("entryPointException.message"));
    }

    // 접근 불가 익셉션
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException exception) {
        return responseService.getFailureResult(Integer.parseInt(getMessage("accessDenied.code")),
                                                                 getMessage("accessDenied.message"));
    }

    // 이미 등록된 계정 등록 시 익셉션
    @ExceptionHandler(CustomRegisteredUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult registeredUserException(HttpServletRequest request, CustomRegisteredUserException exception) {
        return responseService.getFailureResult(Integer.parseInt(getMessage("registeredUser.code")),
                                                                 getMessage("registeredUser.message"));
    }

    // Code 정보에 해당하는 메시지를 MessageSource에서 조회한다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // Code 정보와 현재 Locale에 맞는 메시지를 MessageSource에서 조회한다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}