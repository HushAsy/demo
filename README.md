该demo主要作为一个dubbo项目通过maven自动化docker打包插件发布到镜像仓库样例工程。该wiki后面同时会提供k8s部署zk，mysql，应用包的整个过程。该项目大体功能:zk作为注册中心，服务端发布服务，消费端订阅服务，在调用过程中访问mysql数据库。

# application.properties



    spring.application.name=provider
    
    #spring.dubbo.registry.address=${REGISTRY.ADDRESS:120.78.206.183}
    
    spring.registry.port=${REGISTRY.PORT:2181}
    spring.registry.protocol=${REGISTRY.PROTOCOL:zookeeper}
    
    spring.dubbo.protocol.host=${POD_IP:127.0.0.1}
    
    spring.dubbo.protocol.port=${PROTOCOL.PORT:20880}
    spring.dubbo.protocol.name=${PROTOCOL.NAME:dubbo}
    
    spring.dubbo.application.name=${APPLICATION.NAME:dubbo_provider}
    
    spring.zk1=${ZK_0_SERVICE_HOST:120.78.206.183}
    spring.zk2=${ZK_1_SERVICE_HOST:127.0.0.1}
    spring.zk3=${ZK_2_SERVICE_HOST:127.0.0.1}
    
    
    spring.datasource.host=${MYSQL_SERVICE_HOST:120.78.206.183}
    spring.datasource.port=${MYSQL_SERVICE_HOST_PROT:3306}
    spring.datasource.database=${DATASOURCE.DATABASE:k8s}
    
    spring.datasource.username=${DATASOURCE.USERNAME:root}
    spring.datasource.password=${DATASOURCE.PASSWORD:123456}
    spring.datasource.driver-class=${DATASOURCE.DRIVER.CLASS:com.mysql.jdbc.Driver}
    spring.datasource.types=${DATASOURCE.TYPE:mysql}
    spring.datasource.max-idle=${MAX_IDLE:10}
    spring.datasource.max-wait=${MAX_WAIT:1000}
    spring.datasource.min-idle=${MIN_IDLE:5}
    spring.datasource.initial-size=${INITIAL_SIZE:5}


通过${param:默认值}这种方式配置参数主要为了在发布到k8s环境里面，加载pod容器设置的env参数；

maven配置docker打包插件





	<plugin>
		<groupId>com.spotify</groupId>
		<artifactId>dockerfile-maven-plugin</artifactId>
		<version>1.4.4</version>
		<executions>
			<execution>
				<id>default</id>
				<goals>
				<goal>build</goal>
				<goal>push</goal>
				</goals>
			</execution>
		</executions>
		<dependencies>
			<dependency>
				<groupId>javax.activation</groupId>
				<artifactId>activation</artifactId>
				<version>1.1.1</version>
			</dependency>
		</dependencies>
		<configuration>
			<contextDirectory>${project.basedir}</contextDirectory>
			<useMavenSettingsForAuth>true</useMavenSettingsForAuth>
			<repository>${registryUrl}/hush/${project.build.finalName}</repository>
			<tag>${imageVersion}</tag>
			<buildArgs>
				<JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
			</buildArgs>
		</configuration>
	</plugin>

样例中使用了dockerfile_maven自动化打包插件，若需要研究具体细节可以查看github:**https://github.com/spotify/dockerfile-maven**


# Dockerfile

    FROM registry.cn-hangzhou.aliyuncs.com/hush/basecontainer:20180929
    
    #添加本地jar包
    RUN mkdir -p /home/admin/app/
    RUN mkdir -p /home/admin/tomcat/
    ENV CATALINA_HOME /home/admin/tomcat/
    #
    ARG JAR_FILE
    
    ADD target/${JAR_FILE} /home/admin/app/
    #
    #
    #
    # 将启动命令写入启动脚本 start.sh
    RUN echo "$JAVA_HOME/bin/java -jar $JAVA_OPTS -Ddubbo.address.ip=$POD_IP -Djava.security.egd=file:/dev/./urandom  /home/admin/app/${JAR_FILE} --spring.profiles.active=prod" > /home/admin/start.sh && chmod +x /home/admin/start.sh
    WORKDIR $CATALINA_HOME
    ENTRYPOINT ["/bin/bash", "/home/admin/start.sh"]





