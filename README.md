# jdm3
Timesheet 2.0 Spring Cloud project with microservices

01: 20210518_1930:<br>
- создание проекта по статье <br>
["Quickstart with Java Spring Boot mircoservices"](https://medium.com/@leo.ertuna/quickstart-with-java-spring-boot-mircoservices-b67d63fd19d1) <br>

02: 20210521_1705:
- добавлен модуль "service-user" для реализации REST-сервиса работы с таблицей "USER";<br>
- добавлены необходимые зависимости для подключения к in-memory СУБД HSQLDB;<br>
- добавлен каталог "_database" со скриптами управления СУБД и файлами БД "Timesheet";<br>  
- реализована логика выборки единичной записи по ID из таблицы "USER";<br>
- настроена конфигурация клиента для регистрации в регистре Service Discovery сервиса Eureka;<br>
- проверена работа сервиса "service_user" через прокси-сервис Zuul;<br>
- локальные URL для проверки работы сервисов:<br>
  1 - ["Домашняя страница Eureka"](http://localhost:9001/) <br>
  2 - ["Актуатор для Eureka"](http://localhost:9001/actuator/health) <br>
  3 - ["Актуатор для Zuul"](http://localhost:9002/actuator/health) <br>
  4 - ["Запрос существующей записи из SERVICE_USER напрямую"](http://localhost:8601/user/1) <br>
  5 - ["Запрос несуществующей записи из SERVICE_USER напрямую"](http://localhost:8601/user/9999) <br>
  6 - ["Запрос записи через прокси сервер Zuul"](http://localhost:9002/service-user/user/1) <br>
