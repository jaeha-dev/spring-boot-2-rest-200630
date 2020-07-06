package com.github.devsjh.controller.v1;

import com.github.devsjh.advice.exception.CustomEmailLoginException;
import com.github.devsjh.domain.User;
import com.github.devsjh.model.response.SingleResult;
import com.github.devsjh.service.ResponseService;
import com.github.devsjh.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @title  : 계정 등록/로그인 컨트롤러 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final SignService signService;
    private final ResponseService responseService;

    @ApiOperation(value = "계정 등록", notes = "계정을 등록한다.")
    @PostMapping(value = "/join")
    public SingleResult<User> join(@ApiParam(value = "사용자 이름", required = true) @RequestParam String name,
                                   @ApiParam(value = "계정 이메일", required = true) @RequestParam String email,
                                   @ApiParam(value = "계정 비밀번호", required = true) @RequestParam String password) {

        return responseService.getSingleResult(signService.join(name, email, password));
    }

    @ApiOperation(value = "계정 로그인", notes = "이메일/비밀번호로 로그인한다.")
    @PostMapping(value = "/login")
    public SingleResult<String> login(@ApiParam(value = "계정 이메일", required = true) @RequestParam String email,
                                      @ApiParam(value = "계정 비밀번호", required = true) @RequestParam String password) {

        User user = signService.login(email, password).orElseThrow(CustomEmailLoginException::new);

        return responseService.getSingleResult(signService.createToken(user));
    }
}