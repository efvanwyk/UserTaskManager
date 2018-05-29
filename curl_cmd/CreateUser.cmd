@ECHO OFF
SETLOCAL
CD /d %~dp0

IF [%1] == [] GOTO USAGE
IF [%2] == [] GOTO USAGE
IF [%3] == [] GOTO USAGE

curl -i -H "Content-Type: application/json" -X POST -d "{\"username\":\"%1\",\"first_name\":\"%2\", \"last_name\":\"%3\"}" http://localhost:80/api/user

GOTO END

:USAGE
ECHO. 	Usage: CreateUser.cmd [username] [firstname] [lastname]

:END
