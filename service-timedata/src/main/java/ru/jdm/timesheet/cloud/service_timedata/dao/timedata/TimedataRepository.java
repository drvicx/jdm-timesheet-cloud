package ru.jdm.timesheet.cloud.service_timedata.dao.timedata;

import org.springframework.data.jpa.repository.Query;
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

    //--2021.09.11 18:43
    //  find records by Year and Month
    //List<Timedata> findByYearAndMonth(Short year, Short month);
    //--errors:
    //  - cannot resolve property 'year'
    //  - cannot resolve property 'month'

    //--2021.09.11 21:20
    //  find records by Year and Month
    @Query(value="select * from timedata td where year(td.date) = ?1 and month(td.date) = ?2", nativeQuery = true)
    List<Timedata> findByYearAndMonth(Short year, Short month);

    //--2021.09.12 22:40
    //  find records by UserID, Year and Month
    @Query(value="select * from timedata td where td.userid = ?1 and year(td.date) = ?2 and month(td.date) = ?3", nativeQuery = true)
    List<Timedata> findByUserIdYearMonth(Long userId, Short year, Short month);

}
