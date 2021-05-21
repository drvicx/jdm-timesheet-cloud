@ECHO off
rem chcp 866		--DOS
rem chcp 1251		--Windows(кириллица)
rem chcp 1252		--Windows
rem chcp 65001		--UTF-8
chcp 65001 >nul

echo =Утилита подключения к СУБД HSQLDB запускается..
echo.

cd "C:\Program Files\Java\hsqldb-2.5.1\hsqldb\data\"
start /min java -classpath "C:\Program Files\Java\hsqldb-2.5.1\hsqldb\lib\hsqldb.jar" org.hsqldb.util.DatabaseManagerSwing %1 %2 %3 %4 %5 %6 %7 %8 %9
