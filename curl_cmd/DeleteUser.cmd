@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE

curl -i -H "Content-Type: application/json" -X DELETE http://localhost:8080/api/user/%1

GOTO END

:USAGE
ECHO. 	Usage: DeleteUser.cmd [user_id]

:END
