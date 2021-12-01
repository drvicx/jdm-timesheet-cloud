package ru.jdm.timesheet.cloud.service_timesheet.dao;

import ru.jdm.timesheet.cloud.service_timesheet.entity.Orgdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


/**
 *=Spring Data JPA Interface
 */
@CrossOrigin("http://localhost:4200")
public interface OrgdataRepository extends JpaRepository<Orgdata, Long> {
    //--custom methods

    //--Query method for get paginated Orgdata data by ID (with param "id")
    //  - on the background JPA executes SQL like: select * from orgdata where id=?
    //  - Spring Data REST automagically exposes endpoint for this method and process parameter "id", for example:
    //    http://localhost:8600/api/orgdatas/search/findById?id=1
    //
    //--page data is redundant on single record
    //Page<Orgdata> findById(@RequestParam("id") Long id, Pageable pageable);
    //
    //--FIXED
    //  http://localhost:8600/api/orgdatas/search/findByRecordId?id=1
    Orgdata findByRecordId(@RequestParam("id") Long id);

}
