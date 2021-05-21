@echo off

rem Фиксирует изменения
rem Меняется штамп времени и версия в файле "hsqldb\timesheet.properties"

set TOOL="C:\Program Files\Java\hsqldb-2.5.1\hsqldb\lib\sqltool.jar"
set IP=127.0.0.1
set PORT=9000
set DB=timesheet

java.exe -jar %TOOL% --sql "CHECKPOINT DEFRAG;" --inlineRC url=jdbc:hsqldb:hsql://%IP%:%PORT%/%DB%,User=SA,Password=

pause