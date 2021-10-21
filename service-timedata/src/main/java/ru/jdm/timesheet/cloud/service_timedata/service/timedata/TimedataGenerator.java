package ru.jdm.timesheet.cloud.service_timedata.service.timedata;

import ru.jdm.timesheet.cloud.service_timedata.entity.timedata.Timedata;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 *=PREDEFINED TIMEDATA GENERATOR | ГЕНЕРАТОР ПРЕДУСТАНОВЛЕННЫХ ДАННЫХ
*/
@Service
public class TimedataGenerator {

    //--get FIRST day of given year.month
    //  *2021.10.01 09:15, 2021.10.08 12:50
    //private int getFirstDay(int year, int month) {
    private int getFirstDayOfMonth(int year, int month) {
        //--2021.09.13 21:20
        //--но нам нужно не 1 Объект, а по кол-ву дней в Месяце (вся сетка дней месяца)
        //1. создаем объект для поля "date" типа LocalDate используя значения переданных в метод аргументов
        LocalDate userDate = LocalDate.of(year, month, 1);
        //2. определяем первую и последнюю Дату и День месяца по Месяцу
        LocalDate firstDate = userDate.with(TemporalAdjusters.firstDayOfMonth());
        //--return first day of month
        return firstDate.getDayOfMonth();
    }

    //--get LAST day of given year.month
    //  *2021.10.01 09:15, 2021.10.08 12:50
    //private int getLastDay(int year, int month) {
    private int getLastDayOfMonth(int year, int month) {
        //--2021.09.13 21:20
        //--но нам нужно не 1 Объект, а по кол-ву дней в Месяце (вся сетка дней месяца)
        //1. создаем объект для поля "date" типа LocalDate используя значения переданных в метод аргументов
        LocalDate userDate = LocalDate.of(year, month, 1);
        //2. определяем первую и последнюю Дату и День месяца по Месяцу
        LocalDate lastDate = userDate.with(TemporalAdjusters.lastDayOfMonth()); 	//= 2021-09-30
        //--return last day of month
        return lastDate.getDayOfMonth();
    }

    //--get/set DEFAULT Timedata Object | Создаем Объект по-умолчанию для вывода предустановленных данных
    //  *2021.10.08 12:50
    private List<Timedata> getDefaultTimedataObjects(Long userId, Short year, Short month) {
        //--создаем пустой Список Объектов Timedata
        //List<Timedata> preDefinedDataList = new ArrayList<>();

        //--вызываем Метод класса для определения первого и последнего дня Месяца
        int firstDay = getFirstDayOfMonth(year, month);                                     //= 1
        int lastDay = getLastDayOfMonth(year, month);                                       //= 30

        //WARN: Local variable 'preDefinedDataList' is redundant  -- Inline variable
        //      List<Timedata> preDefinedDataList = IntStream.range(firstDay, lastDay).mapToObj(i -> {
        //              ..
        //          }).collect(Collectors.toList());
        //      return preDefinedDataList;
        //
        //--fixed
        //--возвращаем список Объектов с предустановленными значениями не создавая промежуточную переменную
        //  *дефолтные значения Timedata для всех Сотрудников за 1 конкретно указанный Месяц (Год.Месяц)
        //  *если не сделать (lastDay + 1), то будет генерироваться на 1 меньше в месяце
        return IntStream.range(firstDay, lastDay + 1).mapToObj(i -> {
                    //--создаем пустой Объект
                    Timedata timeData = new Timedata();
                    //--инициализируем поля объекта дефолтными значениями
                    //  *id объекта/записи явно не заполняем: timeData.setId(null) - не нужно
                    timeData.setUserId(userId);
                    timeData.setDate((LocalDate.of(year, month, i)));
                    timeData.setHour(0);
                    timeData.setType("н/д");
                    //--возвращаем созданный объект с инициализированными полями
                    return timeData;
                }
        ).collect(Collectors.toList());
    }


    //--get predefined timesheet records by Year and Month
    //  *2021.10.08 12:50
    public List<Timedata> getDefaultDataByYearAndMonth(Short year, Short month) {
        //--1. создаем Объект для поля "date" типа LocalDate используя значения параметров year.month
        //LocalDate userDate = LocalDate.of(year, month, 1);

        //--2. определяем первую и последнюю Дату и День месяца по параметрам year.month
        //LocalDate firstDate = userDate.with(TemporalAdjusters.firstDayOfMonth()); 	    //= 2021-09-01
        //LocalDate lastDate = userDate.with(TemporalAdjusters.lastDayOfMonth()); 	        //= 2021-09-30
        //int firstDay = firstDate.getDayOfMonth();                                         //= 1
        //int lastDay = lastDate.getDayOfMonth();                                           //= 30

        //--вызываем Метод класса для определения первого и последнего дня Месяца
        //int firstDay = getFirstDayOfMonth(year, month);                                     //= 1
        //int lastDay = getLastDayOfMonth(year, month);                                       //= 30

        //--3. создаем пустой Список Объектов Timedata
        //List<Timedata> preDefinedDataList = new ArrayList<Timedata>();   //--WARN: Explicit type argument Timedata can be replaced with <>
        List<Timedata> preDefinedDataList = new ArrayList<>();

        //--4. генерируем Объекты типа Timedata и добавляем их в Список
        //  *реализация вложенного цикла for через Java 1.8 StreamAPI
        //--во внешнем цикле:
        //   обходим Список идентификаторов Сотрудников предполагая что сотрудников 7:
        //   точное кол-во можно определить либо напрямую - запросив данные из таблицы USER через REST-API сервис "service-user",
        //   либо косвенно - SQL-запросом из таблицы TIMEDATA посчитав кол-во уникальных userId за предыдущий/последний месяц;
        /*
        IntStream.range(1, 7).forEach(userId -> {
            preDefinedDataList.addAll(
                //--во внутреннем цикле: по кол-ву дней в месяце с firstDay по lastDay создаем Объекты и добавляем их в Список
                IntStream.range(firstDay, lastDay).mapToObj(i -> {
                        //--создаем пустой Объект
                        Timedata timeData = new Timedata();
                        //--инициализируем поля объекта дефолтными значениями (id объекта/записи не заполняем)
                        //  @Column(name="ID")       -- private Long        id;
                        //  @Column(name="USERID")   -- private Long        userId;
                        //  @Column(name="DATE")     -- private LocalDate   date;
                        //  @Column(name="HOUR")     -- private Integer     hour;
                        //  @Column(name="TYPE_")    -- private String      type;
                        //--
                        //timeData.setId(null);                             //--идентификатор записи явно задавать не нужно;
                        //timeData.setUserId(Long.valueOf(userId));         //--WARN: Unnecessary boxing 'Long.valueOf(userId)'
                        timeData.setUserId((long) userId);
                        timeData.setDate((LocalDate.of(year, month, i)));
                        timeData.setHour(0);
                        timeData.setType("н/д");
                        //--возвращаем созданный объект с инициализированными полями
                        return timeData;
                    }
                ).collect(Collectors.toList())
            );
        });
        */

        //--оптимизация
        //  getDefaultTimedataObjects((long) userId, null, null)
        //  WARN: Statement lambda can be replaced with expression lambda
        /*
        IntStream.range(1, 7).forEach(userId -> {
            preDefinedDataList.addAll(
                    //--вызываем метод генерации дефолтного-списка
                    getDefaultTimedataObjects((long) userId, year, month)
            );
        });
        */

        //--оптимизация + исправление
        //  getDefaultTimedataObjects((long) userId, null, null)
        //  WARN: Statement lambda can be replaced with expression lambda
        IntStream.range(1, 7).forEach(userId -> preDefinedDataList.addAll(
                //--вызываем метод генерации дефолтного-списка
                getDefaultTimedataObjects((long) userId, year, month)
        ));

        //--возвращаем список Объектов с предустановленными значениями
        //  *дефолтные значения Timedata для всех Сотрудников за 1 конкретно указанный Месяц (Год.Месяц)
        return preDefinedDataList;
    }


    //--get predefined timesheet records by UserId, Year and Month
    //  *2021.10.08 12:50
    public List<Timedata> getDefaultDataByUserIdYearMonth(Long userId, Short year, Short month) {
        //--1. создаем Объект для поля "date" типа LocalDate используя значения параметров year.month
        //LocalDate userDate = LocalDate.of(year, month, 1);

        //--2. определяем первую и последнюю Дату и День месяца по параметрам year.month
        //LocalDate firstDate = userDate.with(TemporalAdjusters.firstDayOfMonth()); 	    //= 2021-09-01
        //LocalDate lastDate = userDate.with(TemporalAdjusters.lastDayOfMonth()); 	        //= 2021-09-30
        //int firstDay = firstDate.getDayOfMonth();                                         //= 1
        //int lastDay = lastDate.getDayOfMonth();                                           //= 30

        //--вызываем Метод класса для определения первого и последнего дня месяца
        //int firstDay = getFirstDayOfMonth(year, month);                                   //= 1
        //int lastDay = getLastDayOfMonth(year, month);                                     //= 30

        //--3. создаем пустой Список Объектов Timedata
        //--4. генерируем Объекты типа Timedata для 1 Сотрудника за указанный Год.Месяц и добавляем их в Список
        //  *реализация вложенного цикла for через Java 1.8 StreamAPI
        //--в цикле:
        //   по кол-ву дней в месяце с firstDay по lastDay создаем Объекты и добавляем их в Список
        //!WARN1: 'addAll()' call can be replaced with parametrized constructor call
        //        preDefinedDataList.addAll(..);
        //        ..применяем исправления
        //!WARN2: Local variable 'preDefinedDataList' is redundant -- Inline variable
        //        List<Timedata> preDefinedDataList = new ArrayList<>(IntStream.range(firstDay, lastDay).mapToObj(i -> {..
        //        ..отказываемся от исправлений (можно сразу возвращать результат не создавая переменную, но так менее читабельно)
        //!WARN3: Stream may be extended replacing ArrayList -- Fuse ArrayList into the StreamAPI chain
        //        ..применяем исправления
        /*
        List<Timedata> preDefinedDataList = IntStream.range(firstDay, lastDay).mapToObj(i -> {
                    //--создаем пустой Объект
                    Timedata timeData = new Timedata();
                    //--инициализируем поля объекта дефолтными значениями
                    //  *id объекта/записи явно не заполняем: timeData.setId(null) - не нужно
                    timeData.setUserId((long) userId);
                    timeData.setDate((LocalDate.of(year, month, i)));
                    timeData.setHour(0);
                    timeData.setType("н/д");
                    //--возвращаем созданный объект с инициализированными полями
                    return timeData;
                }
        ).collect(Collectors.toList());
        //--возвращаем список Объектов с предустановленными значениями
        //  *дефолтные значения Timedata для 1 Сотрудника за 1 конкретно указанный Месяц (Год.Месяц)
        return preDefinedDataList;
        */

        //--сразу возвращаем Объект списка без создания переменной для хранения ссылки на него
        //--возвращаем список Объектов с предустановленными значениями
        //  *дефолтные значения Timedata для 1 Сотрудника за 1 конкретно указанный Месяц (Год.Месяц)
        /*
        return IntStream.range(firstDay, lastDay).mapToObj(i -> {
                    //--создаем пустой Объект
                    Timedata timeData = new Timedata();
                    //--инициализируем поля объекта дефолтными значениями
                    //  *id объекта/записи явно не заполняем: timeData.setId(null) - не нужно
                    timeData.setUserId((long) userId);
                    timeData.setDate((LocalDate.of(year, month, i)));
                    timeData.setHour(0);
                    timeData.setType("н/д");
                    //--возвращаем созданный объект с инициализированными полями
                    return timeData;
                }
        ).collect(Collectors.toList());
        */

        //--оптимизация
        return getDefaultTimedataObjects(userId, year, month);
    }

}
