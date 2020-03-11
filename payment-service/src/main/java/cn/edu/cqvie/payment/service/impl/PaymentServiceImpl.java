package cn.edu.cqvie.payment.service.impl;

import cn.edu.cqvie.payment.dao.PaymentDao;
import cn.edu.cqvie.payment.domain.Payment;
import cn.edu.cqvie.payment.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentDao.findById(id);
    }

    /**
     * 服务降级
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @HystrixCommand(fallbackMethod = "payInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String payInfoTimeOut(Long id) {
        int timeNumber = 5;
        TimeUnit.SECONDS.sleep(timeNumber);
        return "正常执行完毕～";
    }

    /**
     * 服务熔断
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @HystrixCommand(fallbackMethod = "payInfoTimeOutHandler", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
            // 时间窗口
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
            // 失败率达到多少
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @Override
    public String paymentCircuitBreaker(Long id) {
        int timeNumber = 5;
        TimeUnit.SECONDS.sleep(timeNumber);
        return "正常执行完毕～";
    }

    public String paymentCircuitBreakerHandler(Long id) {
        return "执行超时，调用服务熔断方法～";
    }

    public String payInfoTimeOutHandler(Long id) {
        return "执行超时，调用服务降级方法～";
    }
}
