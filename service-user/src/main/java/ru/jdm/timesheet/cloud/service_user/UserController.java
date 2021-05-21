package ru.jdm.timesheet.cloud.service_user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


/**
 *=USER REST-CONTROLLER
 */
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    //--check if user is exists in db: version1: bad
    //--path processing with PathVariable
    //  URL: http://localhost:8080/timesheet/user/1
    /*
    @GetMapping("{userId}")
    public UserDAO getUser(@PathVariable Long userId) {

        //--logging (to console)
        log.info("Getting user with id ["+ userId +"];");

        //--WARN: 'Optional.get()' without 'isPresent()' check
        //UserDAO user = userRepository.findById(userId).get();

        //--return UerDAO object
        //  error processing is not working
        //return userRepository.findById(userId).orElseThrow(()->new RuntimeException("UserService: USER_NOT_FOUND"));

        //--BAD SOLUTION:
        //  check if exists user record with that ID -> return user object,
        //  if ID is not exist -> return user with ID = 0;
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        } else {
            return userRepository.findById(0L).get();
        }
    }
    */

    //--check if user is exists in db: version2: good (JSON response)
    @GetMapping("{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Long userId) {

        Optional<UserDAO> userOpt = userRepository.findById(userId);

        //--if-else style
        //if (userOpt.isPresent()) {
        //    return new ResponseEntity(userOpt.get(), HttpStatus.OK);
        //} else {
        //    return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        //}

        //--functional style
        return userOpt
                .map(userDAO -> new ResponseEntity<Object>(userDAO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("{\"service_name\": \"service-user\", \"service_msg\": \"USER_NOT_FOUND\", \"http_status\": \""+ HttpStatus.NOT_FOUND +"\"}", HttpStatus.NOT_FOUND));
    }
}
