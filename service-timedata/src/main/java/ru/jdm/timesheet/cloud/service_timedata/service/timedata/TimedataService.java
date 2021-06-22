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

    //--add/save/update single record
    void save(Timedata theTimedata);

    //--delete single record by id
    void deleteById(Long theId);
}
