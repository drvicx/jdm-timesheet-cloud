package ru.jdm.timesheet.cloud.service_timedata.controller;

import org.springframework.http.MediaType;
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
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
    //  http://localhost:8602/api/timedata/all
    @GetMapping("all")
    public List<Timedata> findAll() {
        //--логика проверки перенесена на сервисный слой
        return timedataService.findAll();
    }

    //--READ/GET single record by ID
    //  http://localhost:8602/api/timedata/id/1
    @GetMapping("id/{timedataId}")
    public Timedata getTimedataById(@PathVariable Long timedataId) {
        //--логика проверки перенесена на сервисный слой
        return timedataService.findById(timedataId);
    }

    //--READ/GET records by userId
    //  http://localhost:8602/api/timedata/userid/1
    @GetMapping("userid/{userId}")
    public List<Timedata> getTimedataByUserId(@PathVariable Long userId) {
        //--логика проверки перенесена на сервисный слой
        return timedataService.findByUserId(userId);
    }

    //--READ/GET records by date
    //  http://localhost:8602/api/timedata/date/2020-12-01
    @GetMapping("date/{date}")
    public List<Timedata> getTimedataByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        //--логика проверки перенесена на сервисный слой
        return timedataService.findByDate(date);
    }

    //--READ/GET records by userId and Date
    //  http://localhost:8602/api/timedata/userdate/1/2020-12-01
    @GetMapping("userdate/{userId}/{date}")
    public Timedata getTimedataByUserIdAndDate(@PathVariable Long userId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        //--логика проверки перенесена на сервисный слой
        return timedataService.findByUserIdAndDate(userId, date);
    }

    //  GET all timesheet records by Year and Month
    //  http://localhost:8602/api/timedata/yearmonth/2020/1
    //  *2021.09.11 18:43, 2021.09.29 11:50
    @GetMapping("yearmonth/{year}/{month}")
    public List<Timedata> getTimedataByYearAndMonth(@PathVariable Short year, @PathVariable Short month) {
        //--логика проверки перенесена на сервисный слой
        return timedataService.findByYearAndMonth(year, month);
    }

    //--GET all timesheet records by UserID, Year and Month
    //  http://localhost:8602/api/timedata/useryearmonth/1/2020/12
    //  *2021.09.12 22:40
    @GetMapping("useryearmonth/{userId}/{year}/{month}")
    public List<Timedata> getTimedataByUserIdYearMonth(@PathVariable Long userId, @PathVariable Short year, @PathVariable Short month) {
        //--логика проверки перенесена на сервисный слой
        return timedataService.findByUserIdYearMonth(userId, year, month);
    }

    //--Ендпоинт "/error" на который должен происходить автоматический переход при возникновении HTTP-ошибок (404,500 etc)
    //  http://localhost:8602/api/timedata/error
    //  *2021.09.12 15:20
    //  как это работает (точнее не работает) сейчас:
    //  1. делаем GET запрос который пытается получить несуществующую в БД запись
    //     http://localhost:8602/api/timedata/yearmonth/2022/1
    //  2. открывается страница с ошибкой (url при этом не меняется)
    //          Whitelabel Error Page
    //          This application has no explicit mapping for /error, so you are seeing this as a fallback.
    //          There was an unexpected error (type=Internal Server Error, status=500).
    //  3. а нам нужно чтобы или возвращался кастомный JSON ответ
    //     или происходил редирект на кастомную HTML-страницу
    //  4. сейчас возвращается ответ 2 и отдельно работает ендпоинт /error
    //
    //--не правильно: в "Response Header" возвращается "Content-Type: text/html;charset=UTF-8"
    //                а у нас REST сервис который должен возвращать JSON, а не HTML
    //@GetMapping("error")
    //public String error() {
    //--return html
    //    return "<html>\n" + "<header><title>Error Page</title></header>\n" +
    //           "<body>\n" + "Something goes wrong: data not found or else..\n" + "</body>\n" + "</html>";
    //}

    //--fix: теперь в "Response Header" возвращается "Content-Type: application/json"
    @GetMapping(path = "error", produces = MediaType.APPLICATION_JSON_VALUE)
    public String error() {
        //--return json
        return "{ \"status\": \"error\", \"message\": \"Something goes wrong: data not found or else..\" }";
    }


    //--CREATE/POST new timedata
    //  http://localhost:8602/api/timedata/add
    @PostMapping("add")
    public Timedata addTimedata(@RequestBody Timedata theTimedata) {
        //--call "save" to database method from DAO-object
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
        //--call "save" to database method
        timedataService.save(theTimedata);
        //--needs to return saved object
        return theTimedata;
    }

    //--DELETE existing timedata by ID
    //  http://localhost:8602/api/timedata/delete/1
    //  *2021.09.29 18:20
    //@DeleteMapping("delete/{timedataId}")
    @DeleteMapping(value = "delete/{timedataId}", produces = "application/json")
    public String deleteTimedata(@PathVariable Long timedataId) {
        //--call "delete" method
        //  *логика проверки перенесена на сервисный слой
        timedataService.deleteById(timedataId);
        //--return content-type: text/plain
        //return "TimedataService: Data with provided ID(" + timedataId + ") was Deleted;";
        //--return content-type: ..
        //  строка возвращается в валидном json-формате, но content-type все-равно остается text/plain
        //  после добавления в аннотацию параметра "produces" - content-type стал = application/json
        return "{ \"status\": \"success\", \"message\": \"TimedataService: Data with provided ID(" + timedataId + ") was Deleted;\" }";
    }

}
