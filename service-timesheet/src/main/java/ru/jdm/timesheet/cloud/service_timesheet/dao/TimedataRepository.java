package ru.jdm.timesheet.cloud.service_timesheet.dao;

import ru.jdm.timesheet.cloud.service_timesheet.entity.Timedata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 *=Spring Data JPA Interface
 */
@CrossOrigin("http://localhost:4200")
public interface TimedataRepository extends JpaRepository<Timedata, Long> {
    //--custom methods

    //--Get Single record by ID v1 (default endpoint in Spring Data JPA)
    //  http://localhost:8600/api/timedatas/1

    //--Get Single record by ID v2 -- no pagination
    //  http://localhost:8600/api/timedatas/search/findTimedataById?id=100
    //
    Timedata findTimedataById(@RequestParam("id") Long id);


    //--Query method for get paginated Timedata data by ID (with param "id")
    //  - on the background JPA executes SQL like: select * from timedata where id=?
    //  - Spring Data REST automagically exposes endpoint for this method and process parameter "id", for example:
    //    http://localhost:8600/api/timedatas/search/findById?id=1
    //
    //--page data is redundant on single record
    Page<Timedata> findById(@RequestParam("id") Long id, Pageable pageable);


    //--GET Timedata records (paginated) by UserID (v1) -- with Query method and param "id"
    //  - SQL:
    //    select * from timedata where userid=?
    //  - endpoint:
    //    http://localhost:8600/api/timedatas/search/findByUserId?id=1
    Page<Timedata> findByUserId(@RequestParam("id") Long id, Pageable pageable);
    //
    //!RUNTIME-ERROR(fixed):
    //      Caused by: org.springframework.data.mapping.PropertyReferenceException:
    //          no property id found for type User! Traversed path: Timedata.user.
    //
    //Page<Timedata> findByUser(@RequestParam("id") Long id, Pageable pageable);
    //
    //!HTTP-REQUEST-ERROR:
    //      http://localhost:8600/api/timedatas/search/findByUser?id=1
    //
    //      Caused by: java.lang.IllegalArgumentException:
    //          Parameter value [1] did not match expected type [ru.jdm.timesheet.cloud.service_timesheet.entity.User (n/a)]
    //
    //--FIXED (see changes in Timedata Entity class)


    //--GET Timedata records (listed) by UserID (v2) -- with parametrized native query
    //  http://localhost:8600/api/timedatas/search/findByUserId?id=1
    //@Query(value = "select * from Timedata td where td.userId = :id", nativeQuery = true)
    //List<Timedata> findByUserId(@Param("id") Long id);


    //--find records by Date
    //  http://localhost:8600/api/timedatas/search/findByDate?date=2020-09-01               --failed
    //  http://localhost:8600/api/timedatas/search/findByDate?date=01-09-2020               --failed
    //Page<Timedata> findByDate(@RequestParam("date") LocalDate date, Pageable pageable);
    //
    //--PROBLEM:
    //  - how to auto-covert at Interface level HTTP GET parameter "date" from String format type to LocalDate type?
    //
    //--SOLUTION
    //  - see below

    //--Query method for get paginated Timedata data by "date" (field) (with param "date")
    //  - on the background JPA executes SQL like: select * from timedata where date = '2020-09-01'
    //  - Spring Data REST automagically exposes endpoint for this method and process parameter "id", for example:
    //    http://localhost:8600/api/timedatas/search/findByDate?date=2020-09-01
    //
    Page<Timedata> findByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Pageable pageable);


    //--GET Timedata records by UserID and Date (v1) -- no pagination -- single record
    //  http://localhost:8600/api/timedatas/search/findByUserIdAndDate?id=1&date=2020-09-01
    //  *we dont need pagination for single-record data
    //
    Timedata findByUserIdAndDate(@RequestParam("id") Long id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);
    //
    //=RUNTIME-ERROR:
    //      Caused by: org.springframework.data.mapping.PropertyReferenceException:
    //             No property id found for type User! Traversed path: Timedata.user.
    //
    // FIXED (see changes in Timedata Entity class)

    //--GET Timedata records by UserID and Date (v2) -- with parametrized native query
    //  http://localhost:8600/api/timedatas/search/findByUserIdAndDate?id=1&date=2020-09-01
    //
    //@Query(value = "select * from Timedata td where td.userId = :id and td.date = :date", nativeQuery = true)
    //Timedata findByUserIdAndDate(@RequestParam("id") Long id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);


    //--GET Timedata records by UserID, Year and Month (Native Query)
    //  http://localhost:8600/api/timedatas/search/findByUserIdAndYearMonth?id=2&year=2020&month=09
    //  *we dont need pagination information in our data set - just get full 1 month data of 1 user
    //
    @Query(value = "select * from Timedata td where td.userId = :id and (year(td.date) = :year and month(td.date) = :month)", nativeQuery = true)
    List<Timedata> findByUserIdAndYearMonth(@Param("id") Long id, @Param("year") Short year, @Param("month") Short month);


    //--GET Timedata records of all Users by Year and Month (Native Query)
    //  http://localhost:8600/api/timedatas/search/findByYearMonth?year=2020&month=09
    //  *we dont need pagination information in JSON data set - just get 1 Month data of All Users
    //
    @Query(value = "select * from Timedata td where year(td.date) = :year and month(td.date) = :month", nativeQuery = true)
    List<Timedata> findByYearMonth(@Param("year") Short year, @Param("month") Short month);

}
