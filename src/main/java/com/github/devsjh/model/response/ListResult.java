package com.github.devsjh.model.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * @title  : 응답 결과(N개) 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Getter
@Setter
public class ListResult<T> extends CommonResult {

    // boolean success, int code, String message
    private List<T> list;
}