package com.github.devsjh.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @title  : 응답 결과(공통) 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Getter
@Setter
public class CommonResult {

    @ApiModelProperty(value = "응답 성공 여부 (True / False)")
    private boolean success;

    @ApiModelProperty(value = "응답 코드 (+값: 정상 / -값: 비정상")
    private int code;

    @ApiModelProperty(value = "응답 메시지")
    private String message;
}