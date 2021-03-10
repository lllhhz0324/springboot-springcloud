package com.lhz.orderservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.commonutil.ordervo.CourseWebVoOrder;
import com.lhz.commonutil.ordervo.UcenterMemberOrder;
import com.lhz.orderservice.client.EduClient;
import com.lhz.orderservice.client.VipClient;
import com.lhz.orderservice.entity.Order;
import com.lhz.orderservice.mapper.OrderMapper;
import com.lhz.orderservice.service.OrderService;
import com.lhz.orderservice.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lhz
 * @Description: 订单服务实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private VipClient vipClient;

    /**
     * @author: lhz
     * @Description: 生成订单
     */
    @Override
    public String createOrders(String courseId, String memberId) {

        UcenterMemberOrder userInfoOrder = vipClient.getUserInfoOrder(memberId);

        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
