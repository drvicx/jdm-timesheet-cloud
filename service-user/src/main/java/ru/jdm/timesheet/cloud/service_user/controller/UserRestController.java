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
    private final UserService userService;

    // quick and dirty inject User Service with Constructor Injection
    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    // expose "users/getall" endpoint and return list of users
    // http://localhost:8601/api/users/getall
    // http://localhost:8601/api/users/all
    @GetMapping("all")
    public List<User> findAll() {
        return userService.findAll();
    }

    //--get single record by userId
    //  http://localhost:8601/api/users/getsingle/7
    //  http://localhost:8601/api/users/getbyid/7
    //  http://localhost:8601/api/users/id/7
    //  *2021.10.21
    @GetMapping("id/{userId}")
    public User getUserById(@PathVariable Long userId) {
        //--логика проверки перенесена на сервисный слой
        return userService.findById(userId);
    }

    //--get single record by personalNumber
    //  http://localhost:8601/api/users/getbypersonalnumber/36
    //  http://localhost:8601/api/users/getbynumber/36
    //  http://localhost:8601/api/users/getbynum/36
    //  http://localhost:8601/api/users/num/36
    @GetMapping("num/{personalNumber}")
    public User getUserByPersonalNumber(@PathVariable Long personalNumber) {
        //  *2021.10.21
        //--логика проверки перенесена на сервисный слой
        return userService.findByPersonalNumber(personalNumber);
    }

    //--mapping for POST user - Add new user
    //  http://localhost:8601/api/users/add
    @PostMapping("add")
    public User addUser(@RequestBody User theUser) {
        //--call save to database method from service layer
        userService.save(theUser);
        //--needs to return saved object
        return theUser;
    }

    //--mapping for PUT user - Update existing user by ID
    //  http://localhost:8601/api/users/update/7
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
    //  http://localhost:8601/api/users/delete/7
    @DeleteMapping("delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        //--create object by find record by id
        User tempUser = userService.findById(userId);
        //--throw Exception if null (if finding user is not exists)
        if (tempUser == null) {
            throw new RuntimeException("User ID (" + userId + ") not found;");
        }
        //--now call delete method
        userService.deleteById(userId);
        //--return JSON-message
        return  "User ID ("+ userId +") Deleted;";
    }

}
