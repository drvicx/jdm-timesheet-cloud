package ru.jdm.timesheet.cloud.service_timedata.service.timedata;

import ru.jdm.timesheet.cloud.service_timedata.dao.timedata.TimedataRepository;
import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;


/**
 *=Timedata Service Implementation
 */
@Service
public class TimedataServiceImpl implements TimedataService {

    private final TimedataRepository timedataRepository;

    @Autowired
    public TimedataServiceImpl(TimedataRepository theTimedataRepository) {
        timedataRepository = theTimedataRepository;
    }

    // findAll() Method Implementation
    //--get all records
    @Override
    public List<Timedata> findAll() {
        return timedataRepository.findAll();
    }

    //--get single record by id -- findById(ID) method from CrudRepository.class
    @Override
    public Timedata findById(Long timedataId) {
        //--Fix (Java 8 solution)
        Optional<Timedata> result = timedataRepository.findById(timedataId);

        //--Check if ID is present then return
        Timedata theTimedata;

        //--проверка на отсутствие
        //if (result == null || result.isEmpty()) {..}

        //--проверка на наличие
        if (result.isPresent()) {
            theTimedata = result.get();
        } else {
            //--data not found message/exception
            throw new RuntimeException("TimedataService: Record with provided ID (" + timedataId + ") not found;");
        }
        return theTimedata;
    }

    //--get records by userId
    @Override
    public List<Timedata> findByUserId(Long userId) {
        //--
        List<Timedata> theTimedata = timedataRepository.findByUserId(userId);
        //Timedata theTimedata;
        //--
        if (theTimedata == null || theTimedata.isEmpty()) {
            throw new RuntimeException("TimedataService: Records with provided UserID (" + userId + ") not found;");
        }
        //--return List of Timedata Objects
        return theTimedata;
    }

    //--get records by date
    @Override
    public List<Timedata> findByDate(LocalDate date) {
        //--
        List<Timedata> theTimedata = timedataRepository.findByDate(date);
        //--
        if (theTimedata == null || theTimedata.isEmpty()) {
            throw new RuntimeException("TimedataService: Records with provided Date (" + date + ") not found;");
        }
        //--return List of Timedata Objects
        return theTimedata;
    }

    //--get record by userId and date
    @Override
    public Timedata findByUserIdAndDate(Long userId, LocalDate date) {
        //--Check if Data is present then return
        Timedata theTimedata = timedataRepository.findByUserIdAndDate(userId, date);
        //--проверка на отсутствие
        if (theTimedata == null) {
            throw new RuntimeException("TimedataService: Records with provided UserID (" + userId + ") and Date ("+ date +") not found;");
        }
        //--return single Timedata Object
        //return timedataRepository.findByUserIdAndDate(userId, date);
        return theTimedata;
    }

    //--get timesheet records by Year and Month
    //  *2021.09.11 18:43, 2021.09.29 11:50
    @Override
    public List<Timedata> findByYearAndMonth(Short year, Short month) {
        //  create list of objects
        List<Timedata> theTimedata = timedataRepository.findByYearAndMonth(year, month);

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

    //--get timesheet records by UserID, Year and Month
    //  *2021.09.12 22:40
    @Override
    public List<Timedata> findByUserIdYearMonth(Long userId, Short year, Short month) {
        //--return List of Timedata Objects without checks
        //return timedataRepository.findByUserIdYearMonth(userId, year, month);

        //--create list of objects
        List<Timedata> theTimedata = timedataRepository.findByUserIdYearMonth(userId, year, month);

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

    //--save/update/add new record
    @Override
    public void save(Timedata theTimedata) {
        timedataRepository.save(theTimedata);
    }

    //--delete record by id
    //  *2021.09.29 18:20
    @Override
    public void deleteById(Long timedataId) {
        //--create temp object by find record by id
        Optional<Timedata> tempTimedata = timedataRepository.findById(timedataId);
        //--return result if finding data is exists or throw Exception if not exists)
        if (tempTimedata.isPresent()) {
            //--call "delete" method
            timedataRepository.deleteById(timedataId);
        } else {
            //--if data not found - throw an exception
            throw new RuntimeException("TimedataService: Records with provided ID(" + timedataId + ") not found;");
        }
    }

}
