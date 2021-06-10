# jdm-timesheet-cloud
Timesheet 2.0 Spring Cloud project with microservices
<br>

**=CHANGE LOG**<br>
*new at the beginning <br>

04: 20210602_1400:
- добавлен модуль "service-timedata" для реализации REST-сервиса работы с таблицей "TIMEDATA";<br>
- реализован весь стандартный REST-CRUD функционал;<br>
- проверена работа всех операций с помощью инструмента "Insomnia";<br>

03: 20210531_1450:
- произведен рефакторинг: реализация приложения переведена на Spring Data JPA;<br>
- добавлен способ конфигурирования множественных DataSource;<br>
- добавлен корневой раздел /api для основного приложения "service-user";<br>
- для каждого ендпоинта реализующего CRUD операции добавлен свой алиас (см.ниже);<br>
- проверена работа всех REST-CRUD операций с помощью инструмента "Insomnia";<br>

02: 20210521_1705:
- добавлен модуль "service-user" для реализации REST-сервиса работы с таблицей "USER";<br>
- добавлены необходимые зависимости для подключения к in-memory СУБД HSQLDB;<br>
- добавлен каталог "_database" со скриптами управления СУБД и файлами БД "Timesheet";<br>  
- реализована логика выборки единичной записи по ID из таблицы "USER";<br>
- настроена конфигурация клиента для регистрации в регистре Service Discovery сервиса Eureka;<br>
- проверена работа сервиса "service_user" через прокси-сервис Zuul;<br>
 
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

- HTTP-methods implemented for REST-controllers: <br>
  GET, POST, PUT, DELETE <br>

- HTTP-REST-endpoints and Methods for SERVICE-USER:<br>
  GET     http://localhost:8601/api/users/getall <br>
  GET     http://localhost:8601/api/users/getsingle/1 <br>
  POST    http://localhost:8601/api/users/add <br>
  PUT     http://localhost:8601/api/users/update/1 <br>
  DELETE  http://localhost:8601/api/users/delete/1 <br>

- HTTP-REST-endpoints and Methods for SERVICE-TIMEDATA:<br>
  GET     http://localhost:8602/api/timedata/getall <br>
  GET     http://localhost:8602/api/timedata/getsingle/1 <br>
  POST    http://localhost:8602/api/timedata/add <br>
  PUT     http://localhost:8602/api/timedata/update/1 <br>
  DELETE  http://localhost:8602/api/timedata/delete/1 <br>

- Прочие эндпоинты: <br>
  1 - ["Домашняя страница Eureka"](http://localhost:9001/) <br>
  2 - ["Актуатор для Eureka"](http://localhost:9001/actuator/health) <br>
  3 - ["Актуатор для Zuul"](http://localhost:9002/actuator/health) <br>
  4 - ["Актуатор для Сервиса User"](http://localhost:8601/api/actuator/health) <br>
  5 - ["Сервис USER через Zuul"](http://localhost:9002/service-user/api/users/getall) <br>
  6 - ["Актуатор для Сервиса Timedata"](http://localhost:8602/api/actuator/health) <br>
  7 - ["Сервис Timedata через Zuul"](http://localhost:9002/service-timedata/api/timedata/getall) <br>
  
