package ru.jdm.timesheet.cloud.service_timedata.service.timedata;

import org.apache.commons.collections4.CollectionUtils;
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
    private final TimedataGenerator timedataGenerator;      //--*2021.10.01

    //--Field injection (is not recommended)
    //@Autowired
    //TimedataRepository theTimedataRepository;

    //--Class injection (recommended)
    //  *2021.10.01
    @Autowired
    public TimedataServiceImpl(TimedataRepository timedataRepository, TimedataGenerator timedataGenerator) {
        this.timedataRepository = timedataRepository;
        this.timedataGenerator = timedataGenerator;
    }

    //--get all records
    @Override
    public List<Timedata> findAll() {
        return timedataRepository.findAll();
    }

    //--get single record by id -- findById(ID) method from CrudRepository.class
    @Override
    public Timedata findById(Long timedataId) {
        //--Fix (Java 8 solution)
        Optional<Timedata> result = timedataRepository.findById(timedataId);

        //--Check if ID is present then return
        Timedata theTimedata;

        //--проверка на отсутствие
        //if (result == null || result.isEmpty()) {..}

        //--проверка на наличие
        if (result.isPresent()) {
            theTimedata = result.get();
        } else {
            //--data not found message/exception
            throw new RuntimeException("TimedataService: Record with provided ID (" + timedataId + ") not found;");
        }
        return theTimedata;
    }

    //--get records by userId
    @Override
    public List<Timedata> findByUserId(Long userId) {
        //--
        List<Timedata> theTimedata = timedataRepository.findByUserId(userId);
        //Timedata theTimedata;
        //--
        if (theTimedata == null || theTimedata.isEmpty()) {
            throw new RuntimeException("TimedataService: Records with provided UserID (" + userId + ") not found;");
        }
        //--return List of Timedata Objects
        return theTimedata;
    }

    //--get records by date
    @Override
    public List<Timedata> findByDate(LocalDate date) {
        //--
        List<Timedata> theTimedata = timedataRepository.findByDate(date);
        //--
        if (theTimedata == null || theTimedata.isEmpty()) {
            throw new RuntimeException("TimedataService: Records with provided Date (" + date + ") not found;");
        }
        //--return List of Timedata Objects
        return theTimedata;
    }

    //--get record by userId and date
    @Override
    public Timedata findByUserIdAndDate(Long userId, LocalDate date) {
        //--Check if Data is present then return
        Timedata theTimedata = timedataRepository.findByUserIdAndDate(userId, date);
        //--проверка на отсутствие
        if (theTimedata == null) {
            throw new RuntimeException("TimedataService: Records with provided UserID (" + userId + ") and Date ("+ date +") not found;");
        }
        //--return single Timedata Object
        //return timedataRepository.findByUserIdAndDate(userId, date);
        return theTimedata;
    }

    //--get timesheet records by Year and Month
    //  *2021.09.11 18:43, 2021.09.29 11:50
    @Override
    public List<Timedata> findByYearAndMonth(Short year, Short month) {
        //--create list of objects
        List<Timedata> theTimedata = timedataRepository.findByYearAndMonth(year, month);

        //--check if List of Objects isNot empty
        //if (theTimedata != null || theTimedata.isEmpty()) {
        //if (theTimedata != null && theTimedata.isEmpty()) {
        //--
        if (CollectionUtils.isEmpty(theTimedata)) {
            theTimedata = timedataGenerator.getDefaultDataByYearAndMonth(year, month);
        }
        //--if not empty - return List of Timedata Objects
        return theTimedata;
    }

    //--get timesheet records by UserID, Year and Month
    //  *2021.09.12 22:40, 2021.10.08 12:50
    @Override
    public List<Timedata> findByUserIdYearMonth(Long userId, Short year, Short month) {
        //--create list of objects
        List<Timedata> theTimedata = timedataRepository.findByUserIdYearMonth(userId, year, month);

        //--check if List of Objects isNot empty
        if (CollectionUtils.isEmpty(theTimedata)) {
            theTimedata = timedataGenerator.getDefaultDataByUserIdYearMonth(userId, year, month);
        }
        //--if not empty - return List of Timedata Objects
        return theTimedata;
    }

    //--save/update/add new record
    @Override
    public void save(Timedata theTimedata) {
        timedataRepository.save(theTimedata);
    }

    //--delete record by id
    //  *2021.09.29 18:20
    @Override
    public void deleteById(Long timedataId) {
        //--create temp object by find record by id
        Optional<Timedata> tempTimedata = timedataRepository.findById(timedataId);
        //--return result if finding data is exists or throw Exception if not exists)
        if (tempTimedata.isPresent()) {
            //--call "delete" method
            timedataRepository.deleteById(timedataId);
        } else {
            //--if data not found - throw an exception
            throw new RuntimeException("TimedataService: Records with provided ID(" + timedataId + ") not found;");
        }
    }

}
