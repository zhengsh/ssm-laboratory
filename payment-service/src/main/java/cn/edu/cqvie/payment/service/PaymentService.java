package cn.edu.cqvie.payment.service;

import cn.edu.cqvie.payment.domain.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment findById(Long id);

    String payInfoTimeOut(Long id);

    String paymentCircuitBreaker(Long id);
}
