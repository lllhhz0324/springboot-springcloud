package com.lhz.eduservicemsm.service;

import java.util.Map;

/**
 * @author: lhz
 * @Description: 信息服务接口
 */
public interface MsmService {
    /**
     * @author: lhz
     * @Description: 发送信息方法
     */
    boolean send(Map<String, Object> param, String phone);
}
