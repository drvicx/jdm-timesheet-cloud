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

    //--2021.09.11 18:43
    //  GET all timesheet records by Year and Month
    //  http://localhost:8602/api/timedata/yearmonth/2020/1
    @GetMapping("yearmonth/{year}/{month}")
    public List<Timedata> getTimedataByYearAndMonth(@PathVariable Short year, @PathVariable Short month) {
        //--create list of objects
        //List<Timedata> theTimedata = timedataService.findByYearAndMonth(year, month);

        //  !проверка на null в данном случае НЕ корректная, т.к если не будет записей в БД,
        //   объект все-равно будет создан, но он будет пустым, а нам нужна проверка на содержимое Списка
        //if (theTimedata == null) {
        //    throw new RuntimeException("Timedata with provided Year.Month (" + year + "." + month + ") not found;");
        //}

        //--just return without checks
        //return timedataService.findByYearAndMonth(year, month);

        //--2021.09.12 12:30
        //--проверяем что Список содержит объекты
        //if (theTimedata.isEmpty()) {
            //--тут сразу выбрасывается исключение в лог и приложение продолжает работать,
            //  но хотелось-бы получить ответ об ошибке в виде JSON
        //    throw new RuntimeException("Timedata with provided Year.Month (" + year + "." + month + ") not found;");
        //}
        //--возвращаем результат: возвращать можно или null, или объект того типа, который указан в сигнатуре метода
        //return theTimedata;


        //--2021.09.14 00:40
        //  create list of objects
        List<Timedata> theTimedata = timedataService.findByYearAndMonth(year, month);

        //--check if List of Objects isNot empty
        if (theTimedata.isEmpty()) {

            //1. создаем объект для поля "date" типа LocalDate используя значения переданных в метод аргументов
            LocalDate userDate = LocalDate.of(year, month, 1);

            //2. определяем первую и последнюю Дату и День месяца по Месяцу
            LocalDate firstDate = userDate.with(TemporalAdjusters.firstDayOfMonth()); 	//= 2021-09-01
            LocalDate lastDate = userDate.with(TemporalAdjusters.lastDayOfMonth()); 	//= 2021-09-30
            int firstDay = firstDate.getDayOfMonth();                                   //= 1
            int lastDay = lastDate.getDayOfMonth();                                     //= 30

            //3. заполняем Список Объектов Timedata на лету создавая их
            List<Timedata> preDefinedDataList = new ArrayList<Timedata>();

            //--список идентификаторов пользователей из таблицы USER
            ArrayList<Long> userIds = new ArrayList<Long>();
            userIds.add(1L);
            userIds.add(2L);
            userIds.add(3L);
            userIds.add(4L);
            userIds.add(5L);
            userIds.add(6L);
            userIds.add(7L);

            //--генерируем базу для рандомного id-шника
            //int rndMin = 1000, rndMax = 5000;
            //int randIdBase = (int) ((Math.random() * (rndMax - rndMin)) + rndMin);

            //--устанавливаем начальный идентификатор вручную
            int fromId = 10000;
            int currentId = 0;

            //--во внешнем цикле обходим Список идентификаторов Сотрудников
            for (Long userId : userIds) {
                //--во внутреннем цикле создаем Список Объектов Сотрудников по сетке дней Месяца
                for (int i = firstDay; i <= lastDay; i++) {

                    currentId = ++fromId;                                   //--сначала инкремент к начальному Идентификатору, затем его сохранение;

                    LocalDate newDate = LocalDate.of(year, month, i);
                    Timedata preDefinedData = new Timedata();

                    preDefinedData.setId((long) currentId);                 // @Column(name="ID")       -- private Long        id;
                    preDefinedData.setUserId(userId);                       // @Column(name="USERID")   -- private Long        userId;
                    preDefinedData.setDate(newDate);                        // @Column(name="DATE")     -- private LocalDate   date;
                    preDefinedData.setHour(0);                              // @Column(name="HOUR")     -- private Integer     hour;
                    preDefinedData.setType("нд");                           // @Column(name="TYPE_")    -- private String      type;

                    //--добавляем Объект в список (данные об 1 дне по 1 сотруднику)
                    preDefinedDataList.add(preDefinedData);
                }
            }
            //--возвращаем список Объектов с предустановленными значениями (дефолтные значения Timedata для всех Сотрудников за 1 Месяц)
            return preDefinedDataList;
        }
        //--if not empty - return List of Timedata Objects
        return theTimedata;
    }


    //--2021.09.12 22:40
    //  GET all timesheet records by UserID, Year and Month
    //  http://localhost:8602/api/timedata/useryearmonth/1/2020/12
    @GetMapping("useryearmonth/{userId}/{year}/{month}")
    public List<Timedata> getTimedataByUserIdYearMonth(@PathVariable Long userId, @PathVariable Short year, @PathVariable Short month) {
        //--create list of objects
        List<Timedata> theTimedata = timedataService.findByUserIdYearMonth(userId, year, month);
        //--check if List of Objects isNot empty
        if (theTimedata.isEmpty()) {
            //--if empty - throw exception
            //throw new RuntimeException("Timedata for UserId ("+ userId +") with provided Year.Month (" + year + "." + month + ") not found;");

            //--2021.09.13 18:05
            //  if empty - return pre-defined data

            //-создаем объект для поля "date" типа LocalDate
            //LocalDate timedataDate = LocalDate.of(2021, 9, 1);         //--явно указываем дату
            //LocalDate userDate = LocalDate.of(year, month, 1);         //--создаем дату из параметров: test1

            //-определяем последний день месяца по Году и Месяцу
            //LocalDate lastDay = userDate.with(TemporalAdjusters.lastDayOfMonth()); 	//2021-09-30

            //Timedata preDefinedUserTData = new Timedata();
            //preDefinedUserTData.setId(null);                    // @Column(name="ID")       -- private Long        id;
            //preDefinedUserTData.setUserId(userId);              // @Column(name="USERID")   -- private Long        userId;
            //preDefinedUserTData.setDate(null);                  // @Column(name="DATE")     -- private LocalDate   date;
            //preDefinedUserTData.setDate(userDate);              // test1: дата из параметров - как есть
            //preDefinedUserTData.setDate(lastDay);               // test2: дата из параметров - последний день месяца
            //preDefinedUserTData.setHour(0);                     // @Column(name="HOUR")     -- private Integer     hour;
            //preDefinedUserTData.setType("?");                   // @Column(name="TYPE_")    -- private String      type;

            //--добавляем Объект в список (данные об 1 дне по 1 сотруднику)
            //theTimedata.add(preDefinedUserTData);

            //--2021.09.13 21:20
            //--но нам нужно не 1 Объект, а по кол-ву дней в Месяце (вся сетка дней месяца)

            //1. создаем объект для поля "date" типа LocalDate используя значения переданных в метод аргументов
            LocalDate userDate = LocalDate.of(year, month, 1);

            //2. определяем первую и последнюю Дату и День месяца по Месяцу
            LocalDate firstDate = userDate.with(TemporalAdjusters.firstDayOfMonth()); 	//= 2021-09-01
            LocalDate lastDate = userDate.with(TemporalAdjusters.lastDayOfMonth()); 	//= 2021-09-30

            int firstDay = firstDate.getDayOfMonth();       //= 1
            int lastDay = lastDate.getDayOfMonth();         //= 30

            //3. заполняем Список Объектов Timedata на лету создавая их
            List<Timedata> preDefinedDataList = new ArrayList<Timedata>();
            //--генерируем базу для рандомного id-шника
            int rndMin = 20000, rndMax = 50000;
            int randIdBase = (int) ((Math.random() * (rndMax - rndMin)) + rndMin);
            //Long randId = new Long(randNum);       //!WARNING: Unnecessary boxing 'new Long(randNum)'
            //Long randIdBase = (long) randNum;      //!WARNING: Local variable is redundant

            for (int i = firstDay; i <= lastDay; i++) {

                LocalDate newDate =  LocalDate.of(year, month, i);
                Timedata preDefinedData = new Timedata();

                preDefinedData.setId((long) randIdBase + (long) i);     // @Column(name="ID")       -- private Long        id;
                preDefinedData.setUserId(userId);                       // @Column(name="USERID")   -- private Long        userId;
                preDefinedData.setDate(newDate);                        // @Column(name="DATE")     -- private LocalDate   date;
                preDefinedData.setHour(0);                              // @Column(name="HOUR")     -- private Integer     hour;
                preDefinedData.setType("нд");                           // @Column(name="TYPE_")    -- private String      type;

                //--добавляем Объект в список (данные об 1 дне по 1 сотруднику)
                preDefinedDataList.add(preDefinedData);
            }
            //--возвращаем список Объектов с предустановленными значениями (дефолтные значения Timedata для 1 сотрудника за 1 месяц)
            return preDefinedDataList;
        }
        //--if not empty - return List of Timedata Objects
        return theTimedata;
    }


    //--2021.09.12 15:20
    //--Ендпоинт "/error" на который должен происходить автоматический переход при возникновении HTTP-ошибок (404,500 etc)
    //  http://localhost:8602/api/timedata/error
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
    @DeleteMapping("delete/{timedataId}")
    public String deleteTimedata(@PathVariable Long timedataId) {
        //--create object by find record by id
        Timedata tempTimedata = timedataService.findById(timedataId);
        //--throw Exception if null (if finding timedata is not exists)
        if (tempTimedata == null) {
            throw new RuntimeException("Timedata ID (" + timedataId + ") not found;");
        }
        //--now call "delete" method
        timedataService.deleteById(timedataId);
        //--return JSON-message
        return "Timedata ID (" + timedataId + ") Deleted;";
    }

}
