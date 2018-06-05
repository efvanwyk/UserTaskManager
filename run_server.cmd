@ECHO OFF
SETLOCAL
CD /d %~dp0

SET version=2.0

java -jar target/original-user-task-manager-%version%-SNAPSHOT.jar