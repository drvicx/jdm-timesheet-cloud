# jdm-timesheet-cloud
Timesheet 2.0 Spring Cloud project with microservices
<br>

**=CHANGE LOG**<br>
*new at the beginning <br>

09: 20210930_1115:
<pre>
- проведена оптимизация Контроллера Сервиса Timedata (TimedataRestController): все проверки перенесены на сервисный слой (TimedataServiceImpl);
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
  
- Available endpoints (local): <br>
  0.01 - [Eureka Home Page](http://localhost:9001/) <br>
  0.02 - [Eureka Actuator](http://localhost:9001/actuator/health) <br>
  0.03 - [Zuul Actuator](http://localhost:9002/actuator/health) <br>

  1.01 - [User Service - Actuator](http://localhost:8601/api/actuator/health) <br>
  1.02 - [User Service - getAll](http://localhost:8601/api/users/all) <br>
  1.03 - [User Service - getById](http://localhost:8601/api/users/id/7) <br>
  1.04 - [User Service - getByPersonalNumber](http://localhost:8601/api/users/num/2001) <br>
  
  1.05 - [User Service - Swagger UI](http://localhost:8601/api/swagger-ui.html) <br>
  1.06 - [User Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8601/api/v2/api-docs) <br>
  
  1.07 - [Zuul - User Service - getAll](http://localhost:9002/api/service-user/users/all) <br>
  1.08 - [Zuul - User Service - Swagger UI](http://localhost:9002/api/service-user/swagger-ui.html) <br>
  1.09 - [Zuul - User Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-user/v2/api-docs) <br>
  
  2.01 - [Timedata Service - Actuator](http://localhost:8602/api/actuator/health) <br>
  2.02 - [Timedata Service - getAll](http://localhost:8602/api/timedata/all) <br>
  2.03 - [Timedata Service - getByUserId](http://localhost:8602/api/timedata/userid/7) <br>
  2.04 - [Timedata Service - getByDate](http://localhost:8602/api/timedata/date/2020-12-31) <br>
  2.05 - [Timedata Service - getById](http://localhost:8602/api/timedata/id/853) <br>
  2.06 - [Timedata Service - getByUserIdAndDate](http://localhost:8602/api/timedata/userdate/7/2020-12-31) <br>

  2.07 - [Timedata Service - getTimedataByYearAndMonth](http://localhost:8602/api/timedata/yearmonth/2020/12) <br>
  2.08 - [Timedata Service - getTimedataByUserIdYearMonth](http://localhost:8602/api/timedata/useryearmonth/7/2020/12) <br>
  
  2.09 - [Timedata Service - Swagger UI](http://localhost:8602/api/swagger-ui.html) <br>
  2.10 - [Timedata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8602/api/v2/api-docs) <br>
  
  2.11 - [Zuul - Timedata Service - getAll](http://localhost:9002/api/service-timedata/timedata/all) <br>
  2.12 - [Zuul - Timedata Service - Swagger UI](http://localhost:9002/api/service-timedata/swagger-ui.html) <br>
  2.13 - [Zuul - Timedata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-timedata/v2/api-docs) <br>  

  3.01 - [Orgdata Service - Actuator](http://localhost:8603/api/actuator/health) <br>
  3.02 - [Orgdata Service - getAll](http://localhost:8603/api/orgdata/all) <br>
  3.03 - [Orgdata Service - getById](http://localhost:8603/api/orgdata/id/1) <br>

  3.04 - [Orgdata Service - Swagger UI](http://localhost:8603/api/swagger-ui.html) <br>
  3.05 - [Orgdata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8603/api/v2/api-docs) <br>

  3.06 - [Zuul - Orgdata Service - getAll](http://localhost:9002/api/service-orgdata/orgdata/all) <br>
  3.07 - [Zuul - Orgdata Service - Swagger UI](http://localhost:9002/api/service-orgdata/swagger-ui.html) <br>
  3.08 - [Zuul - Orgdata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-orgdata/v2/api-docs) <br>
  