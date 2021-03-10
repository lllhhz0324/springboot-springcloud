package com.lhz.orderservice.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.lhz.orderservice.entity.Order;
import com.lhz.orderservice.entity.PayLog;
import com.lhz.orderservice.mapper.PayLogMapper;
import com.lhz.orderservice.service.OrderService;
import com.lhz.orderservice.service.PayLogService;
import com.lhz.orderservice.utils.HttpClient;
import com.lhz.servicebase.ComException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lhz
 * @Description: 支付服务实现类
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;
    /**
     * @author: lhz
     * @Description: 生成微信支付二维码
     */
    @Override
    public Map createNatvie(String orderNo) {
        try {
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(wrapper);

            Map m = new HashMap();
            m.put("appid","wx3333333333333");
            m.put("mch_id", "13266053752");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle()); //课程标题
            m.put("out_trade_no", orderNo); //订单号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");

            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"kn9g5v426MKfHT6m9iK73b0QH7X8rKwb"));
            client.setHttps(true);
            client.post();
            String xml = client.getContent();

            Map<String,String> resultMap = WXPayUtil.xmlToMap(xml);

            Map map = new HashMap();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));

            return map;
        }catch(Exception e) {
            throw new ComException(303,"生成二维码失败");
        }

    }

    /**
     * @author: lhz
     * @Description: 查询订单状态
     */
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            Map m = new HashMap<>();
            m.put("appid", "wx3333333333333");
            m.put("mch_id", "13266053752");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            return resultMap;
        }catch(Exception e) {
            return null;
        }
    }

    /**
     * @author: lhz
     * @Description: 更新订单状态
     */
    @Override
    public void updateOrdersStatus(Map<String, String> map) {

        String orderNo = map.get("out_trade_no");

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);

        if(order.getStatus().intValue() == 1) { return; }
        order.setStatus(1);
        orderService.updateById(order);

        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);
        payLog.setTotalFee(order.getTotalFee());

        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));

        baseMapper.insert(payLog);
    }
}
