package ru.jdm.timesheet.cloud.service_timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 *=TIMESHEET REST-SERVICE
 */
//@EnableEurekaClient
@SpringBootApplication
public class ServiceTimesheetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTimesheetApplication.class, args);
    }
}
