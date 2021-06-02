package ru.jdm.timesheet.cloud.service_timedata.dao.timedata;

import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *=Spring Data JPA Timedata DAO/Repository Implementation
 */
public interface TimedataRepository extends JpaRepository<Timedata, Long> {
    //--no need to write any method declarations
}
