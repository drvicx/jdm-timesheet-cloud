package ru.jdm.timesheet.cloud.service_timesheet.dao;

import ru.jdm.timesheet.cloud.service_timesheet.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *=Spring Data JPA Interface
 */
@CrossOrigin("http://localhost:4200")
public interface UserRepository extends JpaRepository<User, Long> {
    //--custom methods

    //--Query method for get NON paginated User data by User ID (with param "id")
    //  - on the background JPA executes SQL like: select * from user where id=?
    //  - Spring Data REST automagically exposes endpoint for this method and process parameter "id", for example:
    //    http://localhost:8600/api/users/search/findByUserId?id=1
    //Page<User> findByUserId(@RequestParam("id") Long id, Pageable pageable);
    //
    //--Page Type is redundant for a single and unique record
    //  example endpoint:
    //  http://localhost:8600/api/users/search/findByUserId?id=1
    User findByUserId(@RequestParam("id") Long id);

    //--Query method for get User data by User PersonalNumber (unique) (with param "num")
    //  - on the background JPA executes SQL like: select * from user where personalnumber=36
    //  - Spring Data REST automagically exposes endpoint for this method and process parameter "num":
    //    http://localhost:8600/api/users/search/findByPersonalNumber?num=36
    User findByPersonalNumber(@RequestParam("num") Long num);

}
