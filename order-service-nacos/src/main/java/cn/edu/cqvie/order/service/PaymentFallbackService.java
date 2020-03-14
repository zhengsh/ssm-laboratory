package cn.edu.cqvie.order.service;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.order.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult paymentSQL(Long id) {
        return new CommonResult(4444, "服务降级返回，" + id, new Payment(id, "error serial"));
    }
}
