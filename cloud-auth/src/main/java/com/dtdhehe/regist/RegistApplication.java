package com.dtdhehe.regist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:18
 * @description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RegistApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistApplication.class,args);
    }

}
