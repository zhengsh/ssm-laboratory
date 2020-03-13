package cn.edu.cqvie.payment.controller;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.payment.domain.Payment;
import cn.edu.cqvie.payment.service.PaymentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String str : services) {
            log.info(" ***** service: {}", str);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVCIE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping("/create")
    public CommonResult<Object> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("=====> 插入结果: {}", result);
        if (result > 0) {
            return new CommonResult<Object>(200, "写入数据成功", result);
        } else {
            return new CommonResult<Object>(444, "写入数据失败");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Object> get(@PathVariable("id") Long id) {
        Payment result = paymentService.findById(id);
        log.info("=====> 测试热部署 <=====");
        log.info("=====> 查询结果: {}", result);
        if (result != null) {
            return new CommonResult<Object>(200, "查询成功, 服务端口: " + this.serverPort, result);
        } else {
            return new CommonResult<Object>(444, "没有对应记录");
        }
    }

    @GetMapping("/payInfo/{id}")
    public CommonResult<Object> payInfo(@PathVariable("id") Long id) {
        String result = paymentService.payInfoTimeOut(id);
        return new CommonResult<Object>(200, "查询成功", result);
    }

    @GetMapping("/paymentCircuitBreaker/{id}")
    public CommonResult<Object> paymentCircuitBreaker(@PathVariable("id") Long id) {
        String result = paymentService.paymentCircuitBreaker(id);
        return new CommonResult<Object>(200, "查询成功", result);
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        return this.serverPort;
    }

    @SneakyThrows
    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout() {
        TimeUnit.SECONDS.sleep(3);
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return "hi，i`am payment zipkin server fall back, welcome to cqvie";
    }

}
