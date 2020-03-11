package cn.edu.cqvie.order.service;

import cn.edu.cqvie.common.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "PAYMENT-SERVICE", fallback = PaymentHystrixFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/payInfo/{id}")
    CommonResult<Object> payInfo(@PathVariable("id") Long id);

    @GetMapping("/payment/paymentCircuitBreaker/{id}")
    CommonResult<Object> paymentCircuitBreaker(@PathVariable("id") Long id);

}
