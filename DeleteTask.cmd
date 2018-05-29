@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE
IF [%2] == [] GOTO USAGE

curl -i -H "Content-Type: application/json" -X DELETE http://localhost:80/api/user/%1/task/%2

GOTO END

:USAGE
ECHO. 	Usage: DeleteTask.cmd [user_id] [task_id]

:END
