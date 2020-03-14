package cn.edu.cqvie.payment.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class FlowLimitController {

    @SneakyThrows
    @GetMapping("/testA")
    public String testA() {
        //TimeUnit.MICROSECONDS.sleep(1800);
        return "----- testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "----- testB";
    }

    //@SneakyThrows
    @SneakyThrows
    @GetMapping("/testD")
    public String testD() {
         TimeUnit.SECONDS.sleep(1);
        int age = 10/0;
        return "----- testD";
    }


    @SneakyThrows
    @GetMapping("/testE")
    public String testE() {
        // TimeUnit.SECONDS.sleep(1);
        log.info("testE 测试异常数");
        int age = 10/0;
        return "----- testE 测试异常数";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(
            @RequestParam(value = "p1", required = false) String p1,
            @RequestParam(value = "p2", required = false) String p2
            ) {
        int age = 10/0;
        return "----- testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException ex) {
        return "----- deal_testHotKey";
    }
}
