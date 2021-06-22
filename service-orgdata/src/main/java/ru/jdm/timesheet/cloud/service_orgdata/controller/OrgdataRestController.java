package ru.jdm.timesheet.cloud.service_orgdata.controller;

import ru.jdm.timesheet.cloud.service_orgdata.entity.orgdata.Orgdata;
import ru.jdm.timesheet.cloud.service_orgdata.service.orgdata.OrgdataService;

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
 *=ORGDATA REST-CONTROLLER
 * http://localhost:8602/api/orgdata
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("orgdata")
public class OrgdataRestController {

    //--Orgdata Service instance
    private final OrgdataService orgdataService;

    //--quick and dirty inject Orgdata Service with Constructor Injection
    @Autowired
    public OrgdataRestController(OrgdataService theOrgdataService) {
        orgdataService = theOrgdataService;
    }

    //--READ/GET all records
    //  http://localhost:8603/api/orgdata/all
    @GetMapping("all")
    public List<Orgdata> findAll() {
        return orgdataService.findAll();
    }

    //--READ/GET single record by ID
    //  http://localhost:8603/api/orgdata/id/1
    @GetMapping("id/{orgdataId}")
    public Orgdata getOrgdataById(@PathVariable Long orgdataId) {
        //--
        Orgdata theOrgdata = orgdataService.findById(orgdataId);
        //--
        if (theOrgdata == null) {
            throw new RuntimeException("Orgdata ID not found:" + orgdataId);
        }
        return theOrgdata;
    }

    //--CREATE/POST new timedata
    //  http://localhost:8603/api/orgdata/add
    @PostMapping("add")
    public Orgdata addOrgdata(@RequestBody Orgdata theOrgdata) {
        //--call save to database method from DAO-object
        orgdataService.save(theOrgdata);
        //--needs to return saved object
        return theOrgdata;
    }

    //--UPDATE/PUT existing timedata by ID
    //  http://localhost:8603/api/orgdata/update/1
    @PutMapping("update/{orgdataId}")
    public Orgdata updateOrgdata(@PathVariable Long orgdataId, @RequestBody Orgdata theOrgdata) {
        //--select record
        theOrgdata.setId(orgdataId);
        //--call save to database method
        orgdataService.save(theOrgdata);
        //--needs to return saved object
        return theOrgdata;
    }

    //--DELETE existing timedata by ID
    //  http://localhost:8603/api/orgdata/delete/1
    @DeleteMapping("delete/{orgdataId}")
    public String deleteOrgdata(@PathVariable Long orgdataId) {
        //--create object by find record by id
        Orgdata tempOrgdata = orgdataService.findById(orgdataId);
        //--throw Exception if null (if finding timedata is not exists)
        if (tempOrgdata == null) {
            throw new RuntimeException("Orgdata ID (" + orgdataId + ") not found;");
        }
        //--now call delete method
        orgdataService.deleteById(orgdataId);
        //--return JSON-message
        return "Orgdata ID (" + orgdataId + ") Deleted;";
    }

}