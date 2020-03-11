package cn.edu.cqvie.order.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsulApplication.class);
    }
}
