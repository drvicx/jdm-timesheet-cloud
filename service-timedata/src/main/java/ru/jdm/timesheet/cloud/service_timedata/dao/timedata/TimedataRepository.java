package ru.jdm.timesheet.cloud.service_timedata.dao.timedata;

import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;


/**
 *=Spring Data JPA Timedata DAO/Repository Implementation
 */
public interface TimedataRepository extends JpaRepository<Timedata, Long> {
    //--non standard methods
    //--find records by User ID
    List<Timedata> findByUserId(Long theId);

    //--find records by Date
    List<Timedata> findByDate(LocalDate date);

    //--find single record by User ID and Date
    Timedata findByUserIdAndDate(Long theId, LocalDate date);
}
