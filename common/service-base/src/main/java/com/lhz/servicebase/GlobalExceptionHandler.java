package com.lhz.servicebase;


import com.lhz.commonutil.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lhz
 * @Description: 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @author: lhz
     * @Description: 全局异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Res error(Exception e) {
        e.printStackTrace();
        return Res.error().message("全局异常");
    }

    //自定义异常
    @ExceptionHandler(ComException.class)
    @ResponseBody
    public Res error(ComException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Res.error().code(e.getCode()).message(e.getMsg());
    }

}
