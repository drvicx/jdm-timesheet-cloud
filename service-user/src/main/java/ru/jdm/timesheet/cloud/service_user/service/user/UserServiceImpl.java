package ru.jdm.timesheet.cloud.service_user.service.user;

import ru.jdm.timesheet.cloud.service_user.dao.user.UserRepository;
import ru.jdm.timesheet.cloud.service_user.entity.user.User;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


/**
 *=User Service Implementation
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserGenerator userGenerator;      //*2021.10.21

    //*2021.10.21
    @Autowired
    public UserServiceImpl(UserRepository theUserRepository, UserGenerator theUserGenerator) {
        //this.userRepository = theUserRepository;
        userRepository = theUserRepository;
        userGenerator = theUserGenerator;
    }

    // findAll() Method Implementation
    //--get all records
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //--get single record by userId
    @Override
    public User findById(Long theId) {
        //--Warning: Required type User provided Optional<User>
        //return userRepository.findById(theId);

        //--Fix (Java 8 solution)
        Optional<User> result = userRepository.findById(theId);

        //--Check if ID is present then return
        User userObj;
        //User userObj = null;

        //--old style: check if record/object is present
        //if (result.isPresent()) {
        //    userObj = result.get();
        //} else {
            // user record not found by ID exception
            //throw new RuntimeException("UserService: Record with provided ID (" + theId + ") not found;");
            //userObj = userGenerator.getDefaultUserObj();
        //}
        //return userObj;

        //--functional style
        userObj = result.orElseGet(userGenerator::getDefaultUserObj);
        return userObj;
    }

    //--get single record by personalNumber
    @Override
    public User findByPersonalNumber(Long personalNumber) {
        //--Fix (Java 8 solution)
        Optional<User> result = userRepository.findByPersonalNumber(personalNumber);
        //--Check if ID is present then return
        User userObj;
        //--check if record/object is present (functional style)
        userObj = result.orElseGet(userGenerator::getDefaultUserObj);
        //--then return
        return userObj;
    }

    //--save/update/add new record
    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    //--delete record by id
    @Override
    public void deleteById(Long theId) {
        userRepository.deleteById(theId);
    }

}
