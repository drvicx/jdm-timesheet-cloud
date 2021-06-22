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

    @Autowired
    public OrgdataServiceImpl(OrgdataRepository theOrgdataRepository) {
        orgdataRepository = theOrgdataRepository;
    }

    // findAll() Method Implementation
    //--get all records
    @Override
    public List<Orgdata> findAll() {
        return orgdataRepository.findAll();
    }

    //--get single record by id
    @Override
    public Orgdata findById(Long theId) {

        //--Check if ID is present then return
        Optional<Orgdata> result = orgdataRepository.findById(theId);
        Orgdata theOrgdata;
        //Orgdata theOrgdata = null;

        if (result.isPresent()) {
            theOrgdata = result.get();
        } else {
            throw new RuntimeException("Did not find orgdata id: " + theId);
        }
        return theOrgdata;
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
