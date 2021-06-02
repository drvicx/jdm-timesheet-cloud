package ru.jdm.timesheet.cloud.service_timedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 *=TIMEDATA REST-SERVICE
 */
@EnableEurekaClient
@SpringBootApplication
public class ServiceTimedataApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTimedataApplication.class, args);
    }
}
