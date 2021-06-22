package ru.jdm.timesheet.cloud.service_user.dao.user;

import ru.jdm.timesheet.cloud.service_user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 *=Spring Data JPA User DAO/Repository Interface
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //--non standard methods
    Optional<User> findByPersonalNumber(Long theId);
}
