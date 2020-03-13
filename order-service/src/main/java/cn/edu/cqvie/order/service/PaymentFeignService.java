package cn.edu.cqvie.order.service;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.order.domain.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient("PAYMENT-SERVICE")
public interface PaymentFeignService {

//    @PostMapping("/payment/create")
//    CommonResult<Object> create(@RequestBody Payment payment);
//
    @GetMapping("/payment/get/{id}")
    CommonResult<Object> get(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();


    @GetMapping("/payment/zipkin")
    String paymentZipkin();
}
