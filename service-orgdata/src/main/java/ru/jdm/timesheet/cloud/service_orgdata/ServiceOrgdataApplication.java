package ru.jdm.timesheet.cloud.service_orgdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 *=ORGDATA REST-SERVICE
 */
@EnableEurekaClient
@SpringBootApplication
public class ServiceOrgdataApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrgdataApplication.class, args);
    }
}
