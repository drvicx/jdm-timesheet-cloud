package ru.jdm.timesheet.cloud.service_orgdata.service.orgdata;

import ru.jdm.timesheet.cloud.service_orgdata.dao.orgdata.OrgdataRepository;
import ru.jdm.timesheet.cloud.service_orgdata.entity.orgdata.Orgdata;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


/**
 *=Orgdata Service Implementation
 */
@Service
public class OrgdataServiceImpl implements OrgdataService {

    private final OrgdataRepository orgdataRepository;
    private final OrgdataGenerator orgdataGenerator;      //*2021.10.21

    @Autowired
    public OrgdataServiceImpl(OrgdataRepository theOrgdataRepository, OrgdataGenerator theOrgdataGenerator) {
        //this.orgdataRepository = theOrgdataRepository;
        orgdataRepository = theOrgdataRepository;
        orgdataGenerator = theOrgdataGenerator;
    }

    // findAll() Method Implementation
    //--get all records
    @Override
    public List<Orgdata> findAll() {
        return orgdataRepository.findAll();
    }

    //--get single record by id
    //  *2021.10.21
    @Override
    public Orgdata findById(Long theId) {

        //--Try to create object - if record is not exists - "result" is NULL (Java 8 solution)
        Optional<Orgdata> result = orgdataRepository.findById(theId);
        Orgdata orgdataObj;

        //--check if record/object is present (functional style)
        orgdataObj = result.orElseGet(orgdataGenerator::getDefaultOrgdataObj);
        //--then return
        return orgdataObj;
    }

    //--save/update/add new record
    @Override
    public void save(Orgdata theOrgdata) {
        orgdataRepository.save(theOrgdata);
    }

    //--delete record by id
    @Override
    public void deleteById(Long theId) {
        orgdataRepository.deleteById(theId);
    }

}
