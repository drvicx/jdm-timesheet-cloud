@echo off

set TOOL="C:\Program Files\Java\hsqldb-2.5.1\hsqldb\lib\sqltool.jar"
set IP=127.0.0.1
set PORT=9000

java.exe -jar %TOOL% --sql "SHUTDOWN;" --inlineRC url=jdbc:hsqldb:hsql://%IP%:%PORT%/timesheet,User=SA,Password=

rem pause