package com.lhz.orderservice.controller;

import com.lhz.commonutil.Res;
import com.lhz.orderservice.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: lhz
 * @Description: 支付服务前端控制器
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /**
     * @author: lhz
     * @Description: 生成微信支付二维码
     */
    @GetMapping("createNative/{orderNo}")
    public Res createNative(@PathVariable String orderNo) {
        Map map = payLogService.createNatvie(orderNo);
        return Res.ok().data(map);
    }

    /**
     * @author: lhz
     * @Description: 查询订单状态
     */
    @GetMapping("queryPayStatus/{orderNo}")
    public Res queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        if(map == null) {
            return Res.error().message("支付出错");
        }
        if(map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrdersStatus(map);
            return Res.ok().message("支付成功");
        }
        return Res.ok().code(300).message("支付中");

    }
}

