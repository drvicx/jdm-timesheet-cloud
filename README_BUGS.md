# jdm-timesheet-cloud
Timesheet 2.0 Spring Cloud project with microservices
<br><br>

**=BUGS**

20210613_0030_1: _"- Swagger UI неправильно определяет URL ендпоинтов при работе через Zuul"_ <br>
-- в разделе тестирования Методов REST-контроллера сервиса не коррректные URL в запросах;<br>
-- в результате методы возвращают ошибку;<br>
-- при работе с сервисом напрямую (без Zuul) проблемы с тестами методов нет;<br>
-- примеры запросов к методу getAll() напрямую и через Zuul:<br>
http://localhost:8601/api/users/getall <br>
http://localhost:9002/service-user/users/getall <br>
-- как видно, через Zuul теряется префикс "/api" в ендпоинте который указан в конфигурации "application.properties" как:<br>
_server.servlet.context-path=/api_ <br>

-- возможно похожая проблема:<br>
["GitHub | Netflix/zuul | Question - Zuul & Swagger UI"](https://github.com/Netflix/zuul/issues/254) <br>
-- Q: _"How can I integrate Zuul with Swagger UI?"_

-- **исправлено!**<br>
источник: ["Spring Cloud | Netflix | 8. Router and Filter: Zuul"](https://cloud.spring.io/spring-cloud-netflix/multi/multi__router_and_filter_zuul.html) <br>
_(см. "application.properties" модуля "cloud-zuul")_