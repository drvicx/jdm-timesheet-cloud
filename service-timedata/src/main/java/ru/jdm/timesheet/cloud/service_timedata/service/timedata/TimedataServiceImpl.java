package ru.jdm.timesheet.cloud.service_timedata.service.timedata;

import ru.jdm.timesheet.cloud.service_timedata.dao.timedata.TimedataRepository;
import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;


/**
 *=Timedata Service Implementation
 */
@Service
public class TimedataServiceImpl implements TimedataService {

    private final TimedataRepository timedataRepository;

    @Autowired
    public TimedataServiceImpl(TimedataRepository theTimedataRepository) {
        timedataRepository = theTimedataRepository;
    }

    // findAll() Method Implementation
    //--get all records
    @Override
    public List<Timedata> findAll() {
        return timedataRepository.findAll();
    }

    //--get single record by id
    @Override
    public Timedata findById(Long theId) {

        //--Warning: Required type Timedata provided Optional<Timedata>
        //return timedataRepository.findById(theId);

        //--Fix (Java 8 solution)
        Optional<Timedata> result = timedataRepository.findById(theId);

        //--Check if ID is present then return
        Timedata theTimedata;
        //Timedata theTimedata = null;

        if (result.isPresent()) {
            theTimedata = result.get();
        } else {
            // we didn't find the timedata with this theId value
            throw new RuntimeException("Did not find timedata id - " + theId);
        }
        return theTimedata;
    }

    //--get records by userId
    @Override
    public List<Timedata> findByUserId(Long theId) {
        //--return List of Timedata Objects
        return timedataRepository.findByUserId(theId);
    }

    //--get records by date
    @Override
    public List<Timedata> findByDate(LocalDate date) {
        //--return List of Timedata Objects
        return timedataRepository.findByDate(date);
    }

    //--get record by userId and date
    @Override
    public Timedata findByUserIdAndDate(Long theId, LocalDate date) {
        //--return single Timedata Object
        return timedataRepository.findByUserIdAndDate(theId, date);
    }

    //--save/update/add new record
    @Override
    public void save(Timedata theTimedata) {
        timedataRepository.save(theTimedata);
    }

    //--delete record by id
    @Override
    public void deleteById(Long theId) {
        timedataRepository.deleteById(theId);
    }

}
