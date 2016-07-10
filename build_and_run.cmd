@ECHO OFF
SETLOCAL
CD /d %~dp0

CALL build_package.cmd
CALL db_cmd\db_setup.cmd
CALL run_server.cmd
