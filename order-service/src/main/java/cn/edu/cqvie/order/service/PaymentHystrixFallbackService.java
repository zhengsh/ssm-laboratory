package cn.edu.cqvie.order.service;

import cn.edu.cqvie.common.domain.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService {

    @Override
    public CommonResult<Object> payInfo(Long id) {
        return new CommonResult(444, "Feign Hystrix Time Out");
    }

    @Override
    public CommonResult<Object> paymentCircuitBreaker(Long id) {
        return new CommonResult(444, "Feign Hystrix Circuit Breaker");
    }
}
