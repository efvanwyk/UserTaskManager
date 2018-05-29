@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE
IF [%2] == [] GOTO USAGE
IF [%3] == [] GOTO USAGE

curl -i -H "Content-Type: application/json" -X PUT -d "{\"first_name\":\"%2\", \"last_name\":\"%3\"}" http://localhost:80/api/user/%1

GOTO END

:USAGE
ECHO. 	Usage: UpdateUser.cmd [user_id] [firstname] [lastname]

:END
