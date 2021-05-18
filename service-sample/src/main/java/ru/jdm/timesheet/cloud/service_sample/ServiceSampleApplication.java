package main.java.ru.jdm.timesheet.cloud.service_sample;

import ru.jdm.timesheet.cloud.IdGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class ServiceSampleApplication {
    public static final String APPLICATION_ID = IdGenerator.uuid();

    public static void main(String[] args) {
        SpringApplication.run(ServiceSampleApplication.class, args);
    }
}
