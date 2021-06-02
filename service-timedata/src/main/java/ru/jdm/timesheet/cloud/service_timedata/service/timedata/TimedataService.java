package ru.jdm.timesheet.cloud.service_timedata.service.timedata;

import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;
import java.util.List;


/**
 *=Timedata Service Interface
 */
public interface TimedataService {
    //--get all records
    public List<Timedata> findAll();
    //--get single record by id
    public Timedata findById(Long theId);
    //--add/save/update record
    public void save(Timedata theTimedata);
    //--delete record by id
    public void deleteById(Long theId);
}
