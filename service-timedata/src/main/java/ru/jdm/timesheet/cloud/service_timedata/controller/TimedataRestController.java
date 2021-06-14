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


/**
 *=TIMEDATA REST-CONTROLLER
 * http://localhost:8602/api/timedata
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("timedata")
public class TimedataRestController {

    // Timedata Service instance
    private TimedataService timedataService;

    // quick and dirty inject Timedata Service with Constructor Injection
    @Autowired
    public TimedataRestController(TimedataService theTimedataService) {
        timedataService = theTimedataService;
    }

    //--mapping for GET-request - Get all records
    //  http://localhost:8602/api/timedata/getall
    @GetMapping("getall")
    public List<Timedata> findAll() {
        return timedataService.findAll();
    }

    //--mapping for GET-request - Get single record by ID
    //  http://localhost:8602/api/timedata/getsingle/1
    @GetMapping("getsingle/{timedataId}")
    public Timedata getTimedata(@PathVariable Long timedataId) {
        //--
        Timedata theTimedata = timedataService.findById(timedataId);
        //--
        if (theTimedata == null) {
            throw new RuntimeException("Timedata ID not found - " + timedataId);
        }
        return theTimedata;
    }

    //--mapping for POST-request - Add new timedata
    //  http://localhost:8602/api/timedata/add
    @PostMapping("add")
    public Timedata addTimedata(@RequestBody Timedata theTimedata) {

        //--MySQL.: also just in case they pass an id in JSON .. set id to 0
        //          this is to force a save of new item .. instead of update
        //--HSQLDB: no need for this trick
        //theTimedata.setTimedataId(0L);

        //--call save to database method from DAO-object
        timedataService.save(theTimedata);
        //--needs to return saved object
        return theTimedata;
    }

    //--mapping for PUT-request - Update existing timedata by ID
    //  http://localhost:8602/api/timedata/update/1
    @PutMapping("update/{timedataId}")
    public Timedata updateTimedata(@PathVariable Long timedataId, @RequestBody Timedata theTimedata) {
        //--search object by id:
        //  if id is not exists - create new record     //--and that is the problem
        //theTimedata.setUserId(timedataId);            //--method not exists
        //theTimedata.setTimedataId(timedataId);        //--and it is also not exists
        theTimedata.setId(timedataId);                  //--correct
        //--call save to database method
        timedataService.save(theTimedata);
        //--needs to return saved object
        return theTimedata;
    }

    //--mapping for DELETE-request - Delete existing timedata by ID
    //  http://localhost:8602/api/timedata/delete/1
    @DeleteMapping("delete/{timedataId}")
    public String deleteTimedata(@PathVariable Long timedataId) {
        //--create object by find record by id
        Timedata tempTimedata = timedataService.findById(timedataId);
        //--throw Exception if null (if finding timedata is not exists)
        if (tempTimedata == null) {
            throw new RuntimeException("Timedata ID not found - " + timedataId);
        }
        //--now call delete method
        timedataService.deleteById(timedataId);
        //--return JSON-message
        return  "Deleted timedata ID - " + timedataId;
    }

}
