package com.lhz.orderservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.orderservice.entity.PayLog;

import java.util.Map;

/**
 * @author: lhz
 * @Description: 支付服务接口
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * @author: lhz
     * @Description: 生成微信二维码接口
     */
    Map createNatvie(String orderNo);

    /**
     * @author: lhz
     * @Description: 查询订单状态
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * @author: lhz
     * @Description: 更新订单状态
     */
    void updateOrdersStatus(Map<String, String> map);
}
