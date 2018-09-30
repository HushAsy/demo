package org.hush.k8s.provider.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * @description:
 * @author: hewater
 * @create: 2018-09-29 11:16
 **/
@Configuration
@Order(3)
public class DataSourceConfig {

    @Value("${spring.datasource.host}")
    private String host;

    @Value("${spring.datasource.port}")
    private String port;

    @Value("${spring.datasource.database}")
    private String dataBase;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String passWord;

    @Value("${spring.datasource.max-idle}")
    private int maxIdle;

    @Value("${spring.datasource.max-wait}")
    private int maxWait;

    @Value("${spring.datasource.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.min-idle}")
    private int minIdle;

    @Value("${spring.datasource.driver-class}")
    private String driverClass;

    @Value("${spring.datasource.types}")
    private String type;

    @Bean
    public DataSource initDataSource(){
        String url = "jdbc:"+type+"://"+host+":"+port+"/"+dataBase;
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setMaxActive(maxIdle);
        dataSource.setMinIdle(minIdle);
        dataSource.setUsername(userName);
        dataSource.setUrl(url);
        dataSource.setPassword(passWord);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }
}
