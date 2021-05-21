@echo off

rem =Работа в Интерактивном режиме
rem  при использовании .rc файла в нем необходимо прописть блок с параметрами подключения к БД
rem  блоков может быть несколько и они начинаются с заголовка "urlid" в котором задается алиас блока

set HSQLDB_HOME="C:/Program Files/Java/hsqldb-2.5.1/hsqldb"
set RCFILE_HOME="C:/Users/adm"

java -jar %HSQLDB_HOME%/lib/sqltool.jar --rcFile %RCFILE_HOME%/sqltool.rc localhost-sa


pause