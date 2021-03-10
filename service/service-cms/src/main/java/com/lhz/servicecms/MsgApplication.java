package com.lhz.servicecms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lhz
 * @Description: 幻灯片服务启动类
 */
@SpringBootApplication
@ComponentScan({"com.lhz"})
@MapperScan("com.lhz.servicemsg.mapper")
public class MsgApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsgApplication.class, args);
    }
}
