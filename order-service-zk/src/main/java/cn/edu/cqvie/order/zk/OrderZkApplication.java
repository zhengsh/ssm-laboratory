package cn.edu.cqvie.order.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderZkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderZkApplication.class);
    }
}
