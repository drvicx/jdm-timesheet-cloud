# jdm-timesheet-cloud
Timesheet 2.0 Spring Cloud project with microservices
<br>

**=CHANGE LOG**<br>
*new at the beginning <br>

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
  0.1 - [Eureka Home Page](http://localhost:9001/) <br>
  0.2 - [Eureka Actuator](http://localhost:9001/actuator/health) <br>
  0.3 - [Zuul Actuator](http://localhost:9002/actuator/health) <br>

  1.1 - [User Service - Actuator](http://localhost:8601/api/actuator/health) <br>
  1.2 - [User Service - getAll](http://localhost:8601/api/users/getall) <br>
  1.3 - [User Service - Swagger UI](http://localhost:8601/api/swagger-ui.html) <br>
  1.4 - [User Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8601/api/v2/api-docs) <br>
  1.5 - [Zuul - User Service - getAll](http://localhost:9002/api/service-user/users/getall) <br>
  1.6 - [Zuul - User Service - Swagger UI](http://localhost:9002/api/service-user/swagger-ui.html) <br>
  1.7 - [Zuul - User Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-user/v2/api-docs) <br>
  
  2.1 - [Timedata Service - Actuator](http://localhost:8602/api/actuator/health) <br>
  2.2 - [Timedata Service - getAll](http://localhost:8602/api/timedata/getall) <br>
  2.3 - [Timedata Service - Swagger UI](http://localhost:8602/api/swagger-ui.html) <br>
  2.4 - [Timedata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:8602/api/v2/api-docs) <br>
  2.5 - [Zuul - Timedata Service - getAll](http://localhost:9002/api/service-timedata/timedata/getall) <br>
  2.6 - [Zuul - Timedata Service - Swagger UI](http://localhost:9002/api/service-timedata/swagger-ui.html) <br>
  2.7 - [Zuul - Timedata Service - Swagger/OpenAPI Specification (JSON)](http://localhost:9002/api/service-timedata/v2/api-docs) <br>  
