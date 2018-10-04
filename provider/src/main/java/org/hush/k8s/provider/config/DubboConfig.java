package org.hush.k8s.provider.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.hush.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: hewater
 * @create: 2018-09-28 14:38
 **/
@Configuration
@Order(2)
public class DubboConfig {

    @Value("${spring.registry.port}")
    private int registryPort;

    @Value("${spring.registry.protocol}")
    private String registryProtocol;

    @Value("${spring.dubbo.protocol.port}")
    private int protocolPort;

    @Value("${spring.dubbo.protocol.name}")
    private String protocolName;

    @Value("${spring.dubbo.application.name}")
    private String appName;

    @Value("${spring.dubbo.protocol.host}")
    private String host;

    @Value("${spring.zk1}")
    private String zkAddress1;

    @Value("${spring.zk2}")
    private String zkAddress2;

    @Value("${spring.zk3}")
    private String zkAddress3;

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        String address = StringUtils.getConfigZkAddress(zkAddress1, zkAddress2, zkAddress3);
        registryConfig.setAddress(address);
        registryConfig.setPort(registryPort);
        registryConfig.setProtocol(registryProtocol);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(protocolPort);
        protocolConfig.setName(protocolName);
        protocolConfig.setHost(host);
        return protocolConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
        return applicationConfig;
    }

}
