package ru.jdm.timesheet.cloud.service_user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collection;


/**
 *=USER TEST REST-CONTROLLER (test-version 3)
 * - pagination example:
 *   http://localhost:8601/users?page=0&size=2
 */
@RestController
@RequestMapping(value = "users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerTest3 {

    @Autowired
    UserRepository userRepository;

    //--get all records from USER table
    @GetMapping("")
    public Collection<UserDAO> getUsers() {
        //--just return users collection
        return (Collection<UserDAO>) userRepository.findAll();
    }
}
