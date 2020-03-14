package cn.edu.cqvie.payment.controller;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.payment.domain.Payment;
import cn.edu.cqvie.payment.myhandler.CustomerBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

//    @GetMapping("/byResource")
//    @SentinelResource(value = "byResource", blockHandler = "handleException")
//    public CommonResult byResource() {
//        return new CommonResult(200,
//                "按资源限定名称限流测试OK",
//                new Payment(1L, "1110"));
//    }
//
//    public CommonResult handleException(BlockException ex) {
//        return new CommonResult(444,
//                "按资源限定名称限流测试OK",
//                ex.getClass().getCanonicalName());
//    }


    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按 URL 限流测试 OK", new Payment(2L, "serial002"));
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "customerBlockHandler2")
    public CommonResult<Payment> customerBlockHandler() {
        return new CommonResult<Payment>(200, "按照用户自定义", new Payment(2020L, "serial0003"));
    }

}
