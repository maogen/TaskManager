package com.tgram.android.task;

import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = {"com.tgram.android.task.controller"})
//注解启动事务
@EnableTransactionManagement
@MapperScan(value = {"com.tgram.android.task.mapper"})
@EnableSwagger2
public class TaskApplication {
    static {
        try {
            //初始化log4j
            String log4jPath = TaskApplication.class.getClassLoader().getResource("").getPath() + "/log4j.properties";
            System.out.println("初始化Log4j。。。。");
            System.out.println("path is " + log4jPath);
            PropertyConfigurator.configure(log4jPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
