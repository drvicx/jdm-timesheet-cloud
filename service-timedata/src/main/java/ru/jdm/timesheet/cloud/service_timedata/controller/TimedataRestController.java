package ru.jdm.timesheet.cloud.service_timedata.controller;

import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;
import ru.jdm.timesheet.cloud.service_timedata.service.timedata.TimedataService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;


/**
 *=TIMEDATA REST-CONTROLLER
 * http://localhost:8602/api/timedata
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("timedata")
public class TimedataRestController {

    //--Timedata Service instance
    private final TimedataService timedataService;

    //--quick and dirty inject Timedata Service with Constructor Injection
    @Autowired
    public TimedataRestController(TimedataService theTimedataService) {
        timedataService = theTimedataService;
    }

    //--READ/GET all records
    //  http://localhost:8602/api/timedata/getall
    //  http://localhost:8602/api/timedata/all
    @GetMapping("all")
    public List<Timedata> findAll() {
        return timedataService.findAll();
    }

    //--READ/GET single record by ID
    //  http://localhost:8602/api/timedata/getsingle/1
    //  http://localhost:8602/api/timedata/id/1
    @GetMapping("id/{timedataId}")
    public Timedata getTimedataById(@PathVariable Long timedataId) {
        //--
        Timedata theTimedata = timedataService.findById(timedataId);
        //--
        if (theTimedata == null) {
            throw new RuntimeException("Timedata ID not found:" + timedataId);
        }
        return theTimedata;
    }

    //--READ/GET records by userId
    //  http://localhost:8602/api/timedata/userid/1
    @GetMapping("userid/{userId}")
    public List<Timedata> getTimedataByUserId(@PathVariable Long userId) {
        //--
        List<Timedata> theTimedata = timedataService.findByUserId(userId);
        //--check is return not empty
        if (theTimedata == null) {
            throw new RuntimeException("Timedata UserID not found:" + userId);
        }
        return theTimedata;
    }

    //--READ/GET records by date
    //  http://localhost:8602/api/timedata/date/2021-03-02
    @GetMapping("date/{date}")
    public List<Timedata> getTimedataByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        //--
        List<Timedata> theTimedata = timedataService.findByDate(date);
        //--check is return not empty
        if (theTimedata == null) {
            throw new RuntimeException("Timedata with provided date (" + date + ") not found;");
        }
        return theTimedata;
    }

    //--READ/GET records by userId and Date
    //  http://localhost:8602/api/timedata/userdate/1/2021-03-02
    @GetMapping("userdate/{userId}/{date}")
    public Timedata getTimedataByUserIdAndDate(@PathVariable Long userId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        //--
        return timedataService.findByUserIdAndDate(userId, date);
    }

    //--CREATE/POST new timedata
    //  http://localhost:8602/api/timedata/add
    @PostMapping("add")
    public Timedata addTimedata(@RequestBody Timedata theTimedata) {
        //--call save to database method from DAO-object
        timedataService.save(theTimedata);
        //--needs to return saved object
        return theTimedata;
    }

    //--UPDATE/PUT existing timedata by ID
    //  http://localhost:8602/api/timedata/update/1
    @PutMapping("update/{timedataId}")
    public Timedata updateTimedata(@PathVariable Long timedataId, @RequestBody Timedata theTimedata) {
        //--select record
        theTimedata.setId(timedataId);
        //--call save to database method
        timedataService.save(theTimedata);
        //--needs to return saved object
        return theTimedata;
    }

    //--DELETE existing timedata by ID
    //  http://localhost:8602/api/timedata/delete/1
    @DeleteMapping("delete/{timedataId}")
    public String deleteTimedata(@PathVariable Long timedataId) {
        //--create object by find record by id
        Timedata tempTimedata = timedataService.findById(timedataId);
        //--throw Exception if null (if finding timedata is not exists)
        if (tempTimedata == null) {
            throw new RuntimeException("Timedata ID (" + timedataId + ") not found;");
        }
        //--now call delete method
        timedataService.deleteById(timedataId);
        //--return JSON-message
        return "Timedata ID (" + timedataId + ") Deleted;";
    }

}
