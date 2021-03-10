package com.lhz.orderservice.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhz.commonutil.JwtUtils;
import com.lhz.commonutil.Res;
import com.lhz.orderservice.entity.Order;
import com.lhz.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: lhz
 * @Description: 订单前端控制器
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @author: lhz
     * @Description: 生成订单
     */
    @PostMapping("createOrder/{courseId}")
    public Res saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderNo =
                orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Res.ok().data("orderId",orderNo);
    }

    /**
     * @author: lhz
     * @Description: 查询订单信息
     */
    @GetMapping("getOrderInfo/{orderId}")
    public Res getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return Res.ok().data("item",order);
    }

    /**
     * @author: lhz
     * @Description: 查询订单状态
     */
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }
}

