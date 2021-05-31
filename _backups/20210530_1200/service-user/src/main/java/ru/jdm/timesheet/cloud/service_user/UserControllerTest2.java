package ru.jdm.timesheet.cloud.service_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *=USER TEST REST-CONTROLLER (test-version 2)
 * - get User by ID with checks is Exists and ErrorHandling
 *   http://localhost:8601/getuser/1      --> {"userId":1,"login":"burnscm","name":"Монтгомери","surname":"Бернс"}
 *   http://localhost:8601/getuser/777    --> {"service_name": "service-user", "service_msg": "USER_NOT_FOUND:77"}
 */
@RestController
@RequestMapping(value = "getuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerTest2 {

    @Autowired
    UserRepository userRepository;

    //--get all records from USER table
    @GetMapping("{userId}")
    public UserDAO getOneUser(@PathVariable Long userId) {
        //--just return UserDAO object or UserException
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
