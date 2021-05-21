package ru.jdm.timesheet.cloud.service_user;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *=USER TEST REST-CONTROLLER
 */
@RestController
public class UserTestController {
    @RequestMapping(value = "/user_test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getAll() {
        return "{\"msg\": \"HELLO_FROM_USER_TEST_SERVICE\"}";
    }
}
