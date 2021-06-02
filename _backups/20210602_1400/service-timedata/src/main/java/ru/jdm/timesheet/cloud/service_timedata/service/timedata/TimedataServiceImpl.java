package ru.jdm.timesheet.cloud.service_timedata.service.timedata;

import ru.jdm.timesheet.cloud.service_timedata.dao.timedata.TimedataRepository;
import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


/**
 *=Timedata Service Implementation
 */
@Service
public class TimedataServiceImpl implements TimedataService {

    private TimedataRepository timedataRepository;

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
        Timedata theTimedata = null;

        if (result.isPresent()) {
            theTimedata = result.get();
        } else {
            // we didn't find the timedata with this theId value
            throw new RuntimeException("Did not find timedata id - " + theId);
        }
        return theTimedata;
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
