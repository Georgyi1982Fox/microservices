package com.georgyi.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.georgyi.customer",
                "com.georgyi.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.georgyi.clients")
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class CustomerApplication{
    public static void main(String[] args){
        SpringApplication.run(CustomerApplication.class, args);
    }
}
