@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE
IF [%2] == [] GOTO USAGE

curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/%1/task/%2
GOTO END

:USAGE
ECHO. 	Usage: GetUserTask.cmd [user_id] [task_id]

:END
