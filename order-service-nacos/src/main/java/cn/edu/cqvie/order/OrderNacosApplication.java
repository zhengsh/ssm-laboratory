package cn.edu.cqvie.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosApplication.class);
    }
}
