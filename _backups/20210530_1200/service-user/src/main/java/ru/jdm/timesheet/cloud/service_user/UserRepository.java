package ru.jdm.timesheet.cloud.service_user;

//import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Collection;


/**
 *=INTERFACE USER REPOSITORY (CrudRepository)
 */
/*
@Repository
public interface UserRepository extends CrudRepository<UserDAO, Long> {
    //--declarations
}
*/


/**
 *=INTERFACE USER REPOSITORY (JpaRepository)
 */
//@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    //--declarations
}
