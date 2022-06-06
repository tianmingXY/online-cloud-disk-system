package com.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * springboot程序启动类
 * 去除mongodb的干扰
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        PageHelperConfig.showStartInfo(); //控制台显示系统访问路径
    }


}
