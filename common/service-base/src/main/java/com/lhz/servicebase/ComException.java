package com.lhz.servicebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhz
 * @Description: 自定义异常类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComException extends RuntimeException {

    private Integer code;

    private String msg;
}
