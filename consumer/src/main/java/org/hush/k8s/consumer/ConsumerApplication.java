package org.hush.k8s.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger", "slf4j");
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
