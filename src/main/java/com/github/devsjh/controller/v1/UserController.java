package com.github.devsjh.controller.v1;

import com.github.devsjh.advice.exception.CustomUserNotFoundException;
import com.github.devsjh.domain.User;
import com.github.devsjh.model.response.CommonResult;
import com.github.devsjh.model.response.ListResult;
import com.github.devsjh.model.response.SingleResult;
import com.github.devsjh.service.ResponseService;
import com.github.devsjh.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @title  : 계정 컨트롤러 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "인증 토큰", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "계정 조회", notes = "모든 계정을 조회한다.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userService.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "인증 토큰", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "계정 조회", notes = "본인 계정을 조회한다.")
    @GetMapping(value = "/user")
    public SingleResult<User> findUserById(@ApiParam(value = "지역 언어", defaultValue = "ko") @RequestParam String lang) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return responseService.getSingleResult(userService.findByEmail(email)
                                                          .orElseThrow(CustomUserNotFoundException::new));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "인증 토큰", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "계정 수정", notes = "본인 계정을 수정한다.")
    @PutMapping(value = "/user")
    public SingleResult<User> editByNameAndPassword(@ApiParam(value = "사용자 이름", required = true) @RequestParam String name,
                                                    @ApiParam(value = "계정 비밀번호", required = true) @RequestParam String password) {

        return responseService.getSingleResult(userService.editByNameAndPassword(name, password));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "인증 토큰", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "계정 삭제", notes = "본인 계정을 삭제한다.")
    @DeleteMapping(value = "/user")
    public CommonResult deleteByEmailAndPassword(@ApiParam(value = "계정 번호", required = true) @RequestParam String email,
                                                 @ApiParam(value = "계정 비밀번호", required = true) @RequestParam String password) {
        userService.deleteByEmailAndPassword(email, password);

        return responseService.getSuccessResult();
    }
}