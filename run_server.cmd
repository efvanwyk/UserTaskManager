@ECHO OFF
SETLOCAL
CD /d %~dp0

java -jar target/user-task-manager-1.0-SNAPSHOT.jar server usertaskmanager.yml