@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE
IF [%2] == [] GOTO USAGE
IF [%3] == [] GOTO USAGE
IF [%4] == [] GOTO USAGE

curl -i -H "Content-Type: application/json" -X POST -d "{\"name\":\"%2\",\"description\":\"%3\", \"date_time\":\"%4\"}" http://localhost:80/api/user/%1/task/

GOTO END

:USAGE
ECHO. 	Usage: CreateTask.cmd [user_id] [taskname] [description] [date_time (yyyy-MM-dd HH:mm:ss)]
ECHO.	- Encapsulate args with single quotes.

:END
