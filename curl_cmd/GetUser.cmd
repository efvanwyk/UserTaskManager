@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE

curl -i -H "Content-Type: application/json" -X GET http://localhost:80/api/user/%1
GOTO END

:USAGE
ECHO. 	Usage: GetUser.cmd [user_id]

:END
