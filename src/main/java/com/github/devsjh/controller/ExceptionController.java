package com.github.devsjh.controller;

import com.github.devsjh.advice.exception.CustomAuthenticationEntryPointException;
import com.github.devsjh.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/error")
public class ExceptionController {

    @GetMapping(value = "/entry-point")
    public CommonResult entryPointException() {

        // /error/entry-point 접근 시 익셉션 발생생
       throw new CustomAuthenticationEntryPointException();
    }

    @GetMapping(value = "/access-denied")
    public CommonResult accessDeniedException() {
        throw new AccessDeniedException("");
    }
}