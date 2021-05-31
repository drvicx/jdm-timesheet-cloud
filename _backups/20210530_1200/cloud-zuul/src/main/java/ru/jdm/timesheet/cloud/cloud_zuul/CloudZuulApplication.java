package ru.jdm.timesheet.cloud.cloud_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * EnableEurekaClient annotation,
 * marks that this app should register itself in Eureka
 * */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class CloudZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudZuulApplication.class, args);
    }
}
