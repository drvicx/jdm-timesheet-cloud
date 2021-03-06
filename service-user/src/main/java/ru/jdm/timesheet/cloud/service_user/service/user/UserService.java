package ru.jdm.timesheet.cloud.service_user.service.user;

import ru.jdm.timesheet.cloud.service_user.entity.user.User;
import java.util.List;


/**
 *=User Service Interface
 */
public interface UserService {
    //--get all records
    public List<User> findAll();

    //--get single record by userId
    public User findById(Long theId);

    //--get single record by personalNumber
    public User findByPersonalNumber(Long theId);

    //--add/save/update record
    public void save(User theUser);

    //--delete record by id
    public void deleteById(Long theId);
}
