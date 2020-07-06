package com.github.devsjh.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @title  : 응답 결과(1개) 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Getter
@Setter
public class SingleResult<T> extends CommonResult {

    // boolean success, int code, String message
    private T data;
}