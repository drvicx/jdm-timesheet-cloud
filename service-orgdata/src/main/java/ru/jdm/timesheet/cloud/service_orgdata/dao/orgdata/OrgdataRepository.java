package ru.jdm.timesheet.cloud.service_orgdata.dao.orgdata;

import ru.jdm.timesheet.cloud.service_orgdata.entity.orgdata.Orgdata;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *=Spring Data JPA Orgdata DAO/Repository Interface
 */
public interface OrgdataRepository extends JpaRepository<Orgdata, Long> {
    //--non standard methods
}
