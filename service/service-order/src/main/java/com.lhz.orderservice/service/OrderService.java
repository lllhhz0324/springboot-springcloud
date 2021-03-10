package com.lhz.orderservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.orderservice.entity.Order;

/**
 * @author: lhz
 * @Description: 订单服务接口
 */
public interface OrderService extends IService<Order> {

    /**
     * @author: lhz
     * @Description: 生成订单
     */
    String createOrders(String courseId, String memberIdByJwtToken);
}
