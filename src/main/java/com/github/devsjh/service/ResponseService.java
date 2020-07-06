package com.github.devsjh.service;

import com.github.devsjh.model.response.CommonResult;
import com.github.devsjh.model.response.ListResult;
import com.github.devsjh.model.response.SingleResult;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @title  : 응답 서비스 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Service
public class ResponseService {

    public enum CommonResponse {
        SUCCESS(0, "성공"),
        FAILURE(1, "실패");

        private final int code;
        private final String message;

        CommonResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    // 1개의 데이터와 성공 응답
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);

        return result;
    }

    // N개의 데이터와 성공 응답
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);

        return result;
    }

    // 데이터 없이 성공 응답만
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);

        return result;
    }

    // 데이터 없이 실패 응답만
    public CommonResult getFailureResult(int code, String message) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);

        return result;
    }

    // 성공 응답 세팅
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }
}