package cn.edu.cqvie.order.controller;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.order.domain.Payment;
import cn.edu.cqvie.order.service.PaymentService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
public class CircleBreakerController {

    private static final String SERVER_URL = "http://payment-service-nacos";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") 无配配置
    //@SentinelResource(value = "fallback", fallback = "handlerFallback") //只负责业务异常
    //@SentinelResource(value = "fallback", blockHandler = "blockHandlerFallback") //只负责sentinel配合着违规
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该ID没有对应记录，空指针异常");
        }
        return result;
    }

    //本例是fallback
    public CommonResult<Payment> handlerFallback(Long id, Throwable t) {
        return new CommonResult(444, "兜底异常, exception 异常：" + t.getMessage(), new Payment(id, null));
    }


    //本例是blockHandler
    public CommonResult<Payment> blockHandlerFallback(Long id, BlockException t) {
        return new CommonResult(444, "blockHandler, 无此流水号：" + t.getMessage(), new Payment(id, null));
    }

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }

}
