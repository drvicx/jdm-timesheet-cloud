# jdm-timesheet-cloud
Timesheet 2.0 - Spring Cloud Timesheet project with microservices
<br>

**=ЛОКАЛЬНЫЕ ССЫЛКИ**<br>
1. [Дамп основной БД HSQLDB (v1.0) - для основных сервисов](https://github.com/drvicx/jdm-timesheet-cloud/tree/main/_database/hsqldb-2.5.1/hsqldb)
2. [Дамп БД HSQLDB (v2.0) - для сервиса "service-timesheet"](https://github.com/drvicx/jdm-timesheet-cloud/tree/main/_database/hsqldb-2.5.1/hsqldb)
2. [Демонстрационные скриншоты rest-сервисов на разных этапах](https://github.com/drvicx/jdm-timesheet-cloud/tree/main/_preview)
<br>

**=CHANGE LOG**<br>
*new at the beginning <br>

11: 20211204_2040:
<pre>
- найдена и исправлена проблема:
  в JSON-ответе для Сущности Timedata НЕ отображалось поле "userId" которое было задействовано в ManyToOne связи;
</pre>

10: 20211201_1750:
<pre>
- укорочены URL-ы для работы с основными сервисами - убраны префиксы сервисов /timedata, /user, /orgdata;
- обновлен дамп основной БД HSQL (v1.0) - обезличены персональные данные (фиктивные имена) + добавлены поля со ссылками на фото сотрудника и портфолио;
- создан доп. сервис "service-timesheet" который делает выборку из всех таблиц БД (Spring Data REST);
- для сервиса "service-timesheet" создана конфигурация включающая отображение полей идентификаторов в JSON-ответе (expose IDs);
- сервис "service-timesheet" временно переведен в режим работы "read-only" (запрещены HTTP-методы: POST,PUT,DELETE);
- в сервисе "service-timesheet" настроена реляционная связь OneToMany -- ManyToOne между Сущностями User и Timedata позволяющая выполнять кросс-запросы (см. ендпоинты 4.01, 4.02);
- в планах создание второй БД HSQL для сервиса (v2.0) "service-timesheet" с полями содержащими ссылки на фото сотрудника и соц.сеть;
</pre>

09: 20211021_1600:
<pre>
- проведена оптимизация Контроллера Сервиса Timedata (TimedataRestController): проверка данных перенесена на сервисный слой (TimedataServiceImpl);
- проведена оптимизация Контроллера Сервиса User (UserRestController): проверка данных перенесена на сервисный слой (UserServiceImpl);
- проведена оптимизация Контроллера Сервиса Orgdata (OrgdataRestController): проверка данных перенесена на сервисный слой (OrgdataServiceImpl);
- для всех сервисов реализован вывод предустановленных данных в случае отсутствия запрашиваемых данных в БД (см.примеры ниже);
- обновлен скрипт формирования БД HSQL (расположен в каталоге "_database/hsqldb-2.5.1/hsqldb");
</pre>

08: 20210915_1820:
<pre>
- в контроллер Сервиса Timedata добавлены новые Методы для обработки GET-запросов возвращающих предустановленные данные:
  - добавлен метод "getTimedataByYearAndMonth" - получение данных по Году и Месяцу;
  - добавлен метод "getTimedataByUserIdYearMonth" - получение данных по ID Сотрудника, Году и Месяцу;
    (см. ссылки в разделе "Available endpoints (local)", пункты 2.07, 2.08);			
- если при выполнении GET-запроса в базе данных отсутствуют записи с указанными параметрами,
  возвращаются некоторые предустановленные значения в виде JSON-ответа:
  - в поле "id" генерируется временный ID записи,
  - в поле "userID" ID сотрудника остается прежним,
  - в поле "date" дата генерируется по сетке указанного в запросе Года и Месяца,
  - в поле "hour" отработанное время устанавливается в 0 для всех записей,
  - в поле "type" тип записи устанавливается как "нд" (нет данных);
  !предустановленные данные при этом НЕ сохраняются в базу т.к инициатором сохранения будет выступать фронтенд;
</pre>

07: 20210622_1220:
<pre>
- переработана таблица USER в БД Timesheet: добавлены новые поля, старые переименованы;
- в контроллере Сервиса User изменены имена GET-эндпоинтов (сокращены названия с getall на all, c getsingle на id);
- в контроллере Сервиса User добавлен Метод getUserByPersonalNumber();
- в контроллере Сервиса Timedata изменены имена GET-эндпоинтов (сокращены названия с getall на all, c getsingle на id);
- в контроллере Сервиса Timedata добавлены Методы getTimedataByUserId(), getTimedataByDate(), getTimedataByUserIdAndDate();
- создана таблица ORGDATA в БД Timesheet;
- реализован Сервис Orgdata для хранения общих сведений об организации;
- с помощью компонента Swagger UI протестированы все CRUD операции сервисов User, Timedata и Orgdata;
</pre>

06: 20210614_2320:
<pre>
- исправлена CORS-ошибка возникающая на стороне Angular REST-Клиента при доступе к ендпоинтам User и Timedata сервисов;
- добавлена @crossorigin аннотация к контроллерам "UserRestContoller" и "TimedataRestController";
</pre>

05: 20210611_1800:
<pre>
- для сервисов User и Timedata реализована REST-спецификация Swagger/OpenAPI;
- теперь все ендпоинты сервисов описаны в разделе "Swagger UI" (см.ссылки ниже);
- описание ошибок и способов их решения вынесены в файл "README_BUGS.md";
- найдена и исправлена ошибка с доступом к ендпоинтам Сервисов через Zuul из Swagger UI;
- в конфигурации Zuul изменен URL для доступа к сервисам.
--было:
  http://localhost:9002/service-user/api/users/getall
--стало:
  http://localhost:9002/api/service-user/users/getall
</pre>

04: 20210602_1400:
<pre>
- добавлен модуль "service-timedata" для реализации REST-сервиса работы с таблицей "TIMEDATA";
- реализован весь стандартный REST-CRUD функционал;
- проверена работа всех операций с помощью инструмента "Insomnia";
</pre>

03: 20210531_1450:
<pre>
- произведен рефакторинг: реализация приложения переведена на Spring Data JPA;
- добавлен способ конфигурирования множественных DataSource;
- добавлен корневой раздел /api для основного приложения "service-user";
- для каждого ендпоинта реализующего CRUD операции добавлен свой алиас (см.ниже);
- проверена работа всех REST-CRUD операций с помощью инструмента "Insomnia";
</pre>

02: 20210521_1705:
<pre>
- добавлен модуль "service-user" для реализации REST-сервиса работы с таблицей "USER";
- добавлены необходимые зависимости для подключения к in-memory СУБД HSQLDB;
- добавлен каталог "_database" со скриптами управления СУБД и файлами БД "Timesheet";
- реализована логика выборки единичной записи по ID из таблицы "USER";
- настроена конфигурация клиента для регистрации в регистре Service Discovery сервиса Eureka;
- проверена работа сервиса "service_user" через прокси-сервис Zuul;
</pre>
 
01: 20210518_1930:<br>
- создание проекта по статье <br>
  ["Quickstart with Java Spring Boot mircoservices"](https://medium.com/@leo.ertuna/quickstart-with-java-spring-boot-mircoservices-b67d63fd19d1) <br>


**=INFO**
- environment:<br>
  Java 1.8.0 <br>
  Maven 3.6.3 <br>

- dependencies:<br>
  Spring Boot 2.4.5 <br>
  Spring Boot Starter Web <br>
  Spring Boot Starter Data JPA <br>
  Spring Boot Starter Actuator <br>
  JDBC HSQLDB 2.5.2 Connector <br>
  Springfox Swagger 2.9.2 <br>
  Springfox Swagger UI 2.9.2 <br>
  <br>
  
- Available endpoints for services "orgdata", "user", "timedata" (local): <br>
  0.01 - [Eureka Home Page](http://localhost:9001/) <br>
  0.02 - [Eureka Actuator](http://localhost:9001/actuator/health) <br>
  0.03 - [Zuul Actuator](http://localhost:9002/actuator/health) <br>

  1.01 - [User Service - Actuator](http://localhost:8601/api/actuator/health) <br>
  1.02 - [User Service - getAll](http://localhost:8601/api/all) <br>
  1.03 - [User Service - getById](http://localhost:8601/api/id/7) <br>
  1.04 - [User Service - getByPersonalNumber](http://localhost:8601/api/num/2001) <br>
  
  1.05 - [User Service - Swagger UI](http://localhost:8601/api/swagger-ui.html) <br>
  1.06 - [User Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8601/api/v2/api-docs) <br>
  
  1.07 - [Zuul - User Service - getAll](http://localhost:9002/api/service-user/all) <br>
  1.08 - [Zuul - User Service - Swagger UI](http://localhost:9002/api/service-user/swagger-ui.html) <br>
  1.09 - [Zuul - User Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-user/v2/api-docs) <br>
  
  2.01 - [Timedata Service - Actuator](http://localhost:8602/api/actuator/health) <br>
  2.02 - [Timedata Service - getAll](http://localhost:8602/api/all) <br>
  2.03 - [Timedata Service - getByUserId](http://localhost:8602/api/userid/7) <br>
  2.04 - [Timedata Service - getByDate](http://localhost:8602/api/date/2020-12-31) <br>
  2.05 - [Timedata Service - getById](http://localhost:8602/api/id/853) <br>
  2.06 - [Timedata Service - getByUserIdAndDate](http://localhost:8602/api/userdate/7/2020-12-31) <br>

  2.07 - [Timedata Service - getTimedataByYearAndMonth](http://localhost:8602/api/yearmonth/2020/12) <br>
  2.08 - [Timedata Service - getTimedataByUserIdYearMonth](http://localhost:8602/api/useryearmonth/7/2020/12) <br>
  
  2.09 - [Timedata Service - Swagger UI](http://localhost:8602/api/swagger-ui.html) <br>
  2.10 - [Timedata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8602/api/v2/api-docs) <br>
  
  2.11 - [Zuul - Timedata Service - getAll](http://localhost:9002/api/service-timedata/timedata/all) <br>
  2.12 - [Zuul - Timedata Service - Swagger UI](http://localhost:9002/api/service-timedata/swagger-ui.html) <br>
  2.13 - [Zuul - Timedata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-timedata/v2/api-docs) <br>  

  3.01 - [Orgdata Service - Actuator](http://localhost:8603/api/actuator/health) <br>
  3.02 - [Orgdata Service - getAll](http://localhost:8603/api/all) <br>
  3.03 - [Orgdata Service - getById](http://localhost:8603/api/id/1) <br>

  3.04 - [Orgdata Service - Swagger UI](http://localhost:8603/api/swagger-ui.html) <br>
  3.05 - [Orgdata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8603/api/v2/api-docs) <br>

  3.06 - [Zuul - Orgdata Service - getAll](http://localhost:9002/api/service-orgdata/orgdata/all) <br>
  3.07 - [Zuul - Orgdata Service - Swagger UI](http://localhost:9002/api/service-orgdata/swagger-ui.html) <br>
  3.08 - [Zuul - Orgdata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-orgdata/v2/api-docs) <br><br>

- Available endpoints for "service-timesheet" (local): <br>
  1.01 - [Timesheet Service - Orgdata - getAll](http://localhost:8600/api/orgdatas) -- multiple records <br>
  1.02 - [Timesheet Service - Orgdata - getById](http://localhost:8600/api/orgdatas/1) -- single record <br>
  1.03 - [Timesheet Service - Orgdata - findByRecordId](http://localhost:8600/api/orgdatas/search/findByRecordId?id=1) -- single <br>

  2.01 - [Timesheet Service - User - getAll](http://localhost:8600/api/users) -- multiple <br>
  2.02 - [Timesheet Service - User - getById](http://localhost:8600/api/users/1) -- single <br>
  2.03 - [Timesheet Service - User - findById](http://localhost:8600/api/users/search/findByUserId?id=1) -- single <br>
  2.04 - [Timesheet Service - User - findByPersonalNumber](http://localhost:8600/api/users/search/findByPersonalNumber?num=562) -- single <br>

  3.01 - [Timesheet Service - Timedata - getAll](http://localhost:8600/api/timedatas) -- paged, 1st 20 records <br>
  3.02 - [Timesheet Service - Timedata - getAll](http://localhost:8600/api/timedatas?page=0&size=30) -- paged, 1st 30 <br>
  3.03 - [Timesheet Service - Timedata - getAll](http://localhost:8600/api/timedatas?page=1&size=30) -- paged, 2nd 30 <br>
  
  3.04 - [Timesheet Service - Timedata - getById](http://localhost:8600/api/timedatas/1) -- single <br>
  3.05 - [Timesheet Service - Timedata - getAll](http://localhost:8600/api/timedatas/search/findTimedataById?id=1) -- paged, 1st 20 <br>
  
  3.06 - [Timesheet Service - Timedata - findByUserId](http://localhost:8600/api/timedatas/search/findByUserId?id=1) -- paged, 1st 20 <br>
  3.07 - [Timesheet Service - Timedata - findByUserId](http://localhost:8600/api/timedatas/search/findByUserId?id=1&page=0&size=30) -- paged, 1st 30 <br>
  3.08 - [Timesheet Service - Timedata - findByUserId](http://localhost:8600/api/timedatas/search/findByUserId?id=1&page=1&size=30) -- paged, 2nd 30 <br>
  
  3.09 - [Timesheet Service - Timedata - findByDate](http://localhost:8600/api/timedatas/search/findByDate?date=2020-09-01) -- paged, 1 day of All users <br>
  3.10 - [Timesheet Service - Timedata - findByUserIdAndDate](http://localhost:8600/api/timedatas/search/findByUserIdAndDate?id=1&date=2020-09-01) -- 1 day of 1 user <br>
  3.11 - [Timesheet Service - Timedata - findByUserIdAndYearMonth](http://localhost:8600/api/timedatas/search/findByUserIdAndYearMonth?id=2&year=2020&month=09) -- 1 month of 1 user <br>
  3.12 - [Timesheet Service - Timedata - findByYearMonth](http://localhost:8600/api/timedatas/search/findByYearMonth?year=2020&month=09) -- 1 month of All users <br>

  4.01 - [Timesheet Service - Relational - Timedatas from User](http://localhost:8600/api/users/2/timedata) -- all Timedata records by User ID <br>
  4.02 - [Timesheet Service - Relational - User from timedata](http://localhost:8600/api/timedatas/31/user) -- single User record/info by Timedata ID <br>

  <br>

**=APP-PREVIEW**

- ответ от сервиса "timesheet-service" - раздел "orgdatas"

![предпросмотр](_preview/preview_20211201_timesheet-service_orgdatas.png?raw=true)

- ответ от сервиса "timesheet-service" - раздел "users"

![предпросмотр](_preview/preview_20211201_timesheet-service_users.png?raw=true)

- ответ от сервиса "timesheet-service" - раздел "timedatas"

![предпросмотр](_preview/preview_20211204_timesheet-service_timedatas.png?raw=true)

- ответ "по-умолчанию" (когда запрашиваемых данных в БД нет) от сервиса "orgdata-service"

![предпросмотр](_preview/preview_20211120_defaultData_orgdata-service.png?raw=true)

- ответ "по-умолчанию" (когда запрашиваемых данных в БД нет) от сервиса "user-service"

![предпросмотр](_preview/preview_20211120_defaultData_user-service.png?raw=true)

- ответ "по-умолчанию" (когда запрашиваемых данных в БД нет) от сервиса "timedata-service"

![предпросмотр](_preview/preview_20211021_defaultData_timedata-service.png?raw=true)
