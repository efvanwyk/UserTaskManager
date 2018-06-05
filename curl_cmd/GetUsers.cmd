@ECHO OFF
SETLOCAL
CD /d %~dp0

curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user
GOTO END

:END
