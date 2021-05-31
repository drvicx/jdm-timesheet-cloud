package ru.jdm.timesheet.cloud.service_user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *=GET USER EXCEPTION
 */
public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long userId) {
        //--String output
        //super("Could not find User " + userId);

        //--JSON output
        //super("{\"service_name\": \"service-user\", \"service_msg\": \"USER["+ userId+ "]_NOT_FOUND\"}");
        super("{\"service_name\": \"service-user\", \"service_msg\": \"USER_NOT_FOUND:"+ userId +"\", \"http_status\": \"404\"}");
    }
}