package com.djcao.boot.handler;

import java.util.HashMap;
import java.util.Map;

import com.djcao.boot.common.PackageResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class LoverControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public PackageResult errorHandler(Exception ex) {
        return PackageResult.error(ex.getMessage());
    }
}
