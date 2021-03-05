package com.tang.dms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tang.dms.mapper")
public class DmsApplication {
    public static void main(String[] args) {
        System.out.println("==============================项目启动==============================");
        SpringApplication.run(DmsApplication.class, args);
        System.out.println("==============================项目启动成功==============================");
    }
}
