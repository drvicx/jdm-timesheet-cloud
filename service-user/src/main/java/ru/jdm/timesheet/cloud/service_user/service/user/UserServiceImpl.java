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

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    // findAll() Method Implementation
    //--get all records
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //--get single record by id
    @Override
    public User findById(Long theId) {

        //--Warning: Required type User provided Optional<User>
        //return userRepository.findById(theId);

        //--Fix (Java 8 solution)
        Optional<User> result = userRepository.findById(theId);

        //--Check if ID is present then return
        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the user with this theId value
            throw new RuntimeException("Did not find user id - " + theId);
        }
        return theUser;
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
