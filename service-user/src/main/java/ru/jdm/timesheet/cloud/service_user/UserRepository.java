package ru.jdm.timesheet.cloud.service_user;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


/**
 *=INTERFACE USER REPOSITORY
 */
@Repository
public interface UserRepository extends CrudRepository<UserDAO, Long> {
    //--declarations
}
