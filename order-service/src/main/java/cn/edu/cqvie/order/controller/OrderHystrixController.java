package cn.edu.cqvie.order.controller;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.order.domain.Payment;
import cn.edu.cqvie.order.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@DefaultProperties(defaultFallback = "payInfoHandler")
public class OrderHystrixController {

    private static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/payment/payinfo/{id}")
//    @HystrixCommand(fallbackMethod = "payInfoHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
//    })
    public CommonResult payInfo(@PathVariable("id") Long id) {
        log.info("输入参数: {}", id);
        return paymentHystrixService.payInfo(id);
    }

    public CommonResult payInfoHandler(@PathVariable("id") Long id) {
        log.info("输入参数: {}", id);
        return new CommonResult(444, "消费方超时，服务降级");
    }

    @GetMapping("/hystrix/payment/paymentCircuitBreaker/{id}")
//    @HystrixCommand(fallbackMethod = "payInfoHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
//    })
    public CommonResult paymentCircuitBreaker(@PathVariable("id") Long id) {
        log.info("输入参数: {}", id);
        return paymentHystrixService.paymentCircuitBreaker(id);
    }
}
