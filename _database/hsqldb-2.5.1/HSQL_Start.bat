@echo off

rem --version0
rem cd /d "C:\Program Files\Java\hsqldb-2.5.1\hsqldb"
rem start java -classpath lib/hsqldb.jar org.hsqldb.server.Server

rem --version1
rem set	CONF="C:\Program Files\Java\hsqldb-2.5.1\hsqldb\server.properties"
rem cd 	"C:\Program Files\Java\hsqldb-2.5.1\hsqldb\lib"

rem java.exe -cp hsqldb.jar org.hsqldb.server.Server --props %CONF%


rem --version2
cd 	"C:\Program Files\Java\hsqldb-2.5.1\hsqldb"

java.exe -cp lib/hsqldb.jar org.hsqldb.server.Server


pause

