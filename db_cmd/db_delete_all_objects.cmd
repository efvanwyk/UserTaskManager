@ECHO OFF
SETLOCAL
CD /d %~dp0
PUSHD ..

java -jar target\user-task-manager-1.0-SNAPSHOT.jar db drop-all --confirm-delete-everything usertaskmanager.yml

POPD
