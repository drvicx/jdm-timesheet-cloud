package ru.jdm.timesheet.cloud.service_user.controller;

import ru.jdm.timesheet.cloud.service_user.entity.user.User;
import ru.jdm.timesheet.cloud.service_user.service.user.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 *=USER REST-CONTROLLER
 * http://localhost:8601/api/users
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserRestController {

    // User Service instance
    private UserService userService;

    // quick and dirty inject User Service with Constructor Injection
    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    // expose "users/getall" endpoint and return list of users
    // http://localhost:8601/api/users/getall
    @GetMapping("getall")
    public List<User> findAll() {
        return userService.findAll();
    }

    //--реализация из урока 464 но без Session слоя
    //  mapping for GET employees/{employeeId}
    //  http://localhost:8601/api/users/getsingle/1
    @GetMapping("getsingle/{userId}")
    public User getUser(@PathVariable Long userId) {
        //--
        User theUser = userService.findById(userId);
        //--
        if (theUser == null) {
            throw new RuntimeException("User ID not found - " + userId);
        }
        return theUser;
    }

    //--mapping for POST user - Add new user
    //  http://localhost:8601/api/users/add
    @PostMapping("add")
    public User addUser(@RequestBody User theUser) {

        //--MySQL.: also just in case they pass an id in JSON .. set id to 0
        //          this is to force a save of new item .. instead of update
        //--HSQLDB: no need for this trick
        //theUser.setUserId(0L);

        //--call save to database method from DAO-object
        userService.save(theUser);
        //--needs to return saved object
        return theUser;
    }

    //--mapping for PUT user - Update existing user by ID
    //  http://localhost:8601/api/users/update/1
    @PutMapping("update/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User theUser) {
        //--search object by id
        theUser.setUserId(userId);
        //--call save to database method from DAO-object
        userService.save(theUser);
        //--needs to return saved object
        return theUser;
    }

    //--mapping for DELETE /users/{userId} - Delete existing user by ID
    //  http://localhost:8601/api/users/delete/1
    @DeleteMapping("delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        //--create object by find record by id
        User tempUser = userService.findById(userId);
        //--throw Exception if null (if finding user is not exists)
        if (tempUser == null) {
            throw new RuntimeException("User ID not found - " + userId);
        }
        //--now call delete method
        userService.deleteById(userId);
        //--return JSON-message
        return  "Deleted user ID - " + userId;
    }

}
