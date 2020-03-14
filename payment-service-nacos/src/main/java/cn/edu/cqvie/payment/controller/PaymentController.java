package cn.edu.cqvie.payment.controller;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.payment.domain.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PaymentController {


    private static Map<Long, Payment> hashMap = new HashMap<>();
    static  {
        hashMap.put(1L, new Payment(1L, "c7d7860b7440477da097f6658cd229da"));
        hashMap.put(2L, new Payment(2L, "1ac1b6766ca64f919e52e264392b7bdc"));
        hashMap.put(3L, new Payment(3L, "e3d2f284e8e34cc3b774c810419c2c53"));
        hashMap.put(4L, new Payment(4L, "64787f03bdf24dddaf3e4fe7c6b7550a"));
    }

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry, serverPort: " + serverPort + "\tid "+ id;
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200,
                "from sql, serverPort: " + serverPort + "\tid " + id, payment);
    }
}
