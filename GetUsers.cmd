@ECHO OFF
SETLOCAL
CD /d %~dp0

curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:80/api/user
GOTO END

:END
