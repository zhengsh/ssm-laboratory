package cn.edu.cqvie.order.controller;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.order.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/feign/payment/get/{id}")
    public CommonResult<Object> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.get(id);
    }

    @GetMapping("/consumer/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign 默认1秒钟超时
        return paymentFeignService.paymentFeignTimeout();
    }
}
