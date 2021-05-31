package ru.jdm.timesheet.cloud.service_user.dao.user;

import ru.jdm.timesheet.cloud.service_user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *=Spring Data JPA User DAO/Repository Implementation
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //--no need to write any method declarations
}
