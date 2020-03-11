package cn.edu.cqvie.payment.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulApplication.class);
    }
}
