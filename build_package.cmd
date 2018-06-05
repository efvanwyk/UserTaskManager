SETLOCAL
CD /d %~dp0

mvn package -e

MKDIR package
XCOPY target/*SNAPSHOT.jar package