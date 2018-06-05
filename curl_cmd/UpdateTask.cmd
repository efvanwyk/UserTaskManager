@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE
IF [%2] == [] GOTO USAGE
IF [%3] == [] GOTO USAGE

curl -i -H "Content-Type: application/json" -X PUT -d '{"name":"%3"}' http://localhost:8080/api/user/%1/task/%2

GOTO END

:USAGE
ECHO. 	Usage: CreateTask.cmd [user_id] [task_id] [taskname]
ECHO.	- Encapsulate args with single quotes.


:END
