package org.hush.k8s.consumer.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: hewater
 * @create: 2018-09-28 19:01
 **/
@Configuration
public class DubboConfig {

    @Value("${spring.dubbo.registry.address}")
    private String registryAddress;

    @Value("${spring.dubbo.registry.port}")
    private int registryPort;

    @Value("${spring.dubbo.registry.protocol}")
    private String registryProtocol;

    @Value("${spring.dubbo.protocol.port}")
    private int protocolPort;

    @Value("${spring.dubbo.protocol.name}")
    private String protocolName;

    @Value("${spring.dubbo.application.name}")
    private String appName;

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registryAddress);
        registryConfig.setPort(registryPort);
        registryConfig.setProtocol(registryProtocol);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(protocolPort);
        protocolConfig.setName(protocolName);
        return protocolConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
        return applicationConfig;
    }

}
