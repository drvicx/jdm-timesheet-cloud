package ru.jdm.timesheet.cloud.service_timedata.service.timedata;

import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;

import java.time.LocalDate;
import java.util.List;


/**
 *=Timedata Service Interface
 */
public interface TimedataService {
    //--get all records
    List<Timedata> findAll();

    //--get single record by id
    Timedata findById(Long theId);

    //--get records by userId
    List<Timedata> findByUserId(Long theId);

    //--get records by date
    List<Timedata> findByDate(LocalDate date);

    //--get single record by userId and date
    Timedata findByUserIdAndDate(Long theId, LocalDate date);

    //--2021.09.11 18:43
    //  get timesheet records by Year and Month
    List<Timedata> findByYearAndMonth(Short year, Short month);

    //--2021.09.12 22:40
    //  get timesheet records by UserID, Year and Month
    List<Timedata> findByUserIdYearMonth(Long userId, Short year, Short month);

    //--add/save/update single record
    void save(Timedata theTimedata);

    //--delete single record by id
    void deleteById(Long theId);
}
