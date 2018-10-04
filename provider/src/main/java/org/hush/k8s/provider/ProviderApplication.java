package org.hush.k8s.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("org.hush.k8s.provider.dao.mapper")
public class ProviderApplication {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(1);
        System.setProperty("dubbo.application.logger", "slf4j");
        SpringApplication.run(ProviderApplication.class, args);
        downLatch.await();
    }
}
