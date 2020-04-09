package cn.edu.cqvie.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@Slf4j
@RequestScope
public class ConfigController {

    @Value("${config.info:hahha}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return this.configInfo;
    }
}
