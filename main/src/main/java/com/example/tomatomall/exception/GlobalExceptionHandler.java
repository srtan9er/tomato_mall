package com.example.tomatomall.exception;

import com.example.tomatomall.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: DingXiaoyu
 * @Date: 0:26 2023/11/26
 * 这个类能够接住项目中所有抛出的异常，
 * 使用了RestControllerAdvice切面完成，
 * 表示所有异常出现后都会通过这里。
 * 这个类将异常信息封装到ResultVO中进行返回。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = TomatoException.class)
    public ResultVO<String> handleAIExternalException(TomatoException e) {
        e.printStackTrace();
        return ResultVO.buildFailure(e.getMessage());
    }
}
