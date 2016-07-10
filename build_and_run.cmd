@ECHO OFF
SETLOCAL
CD /d %~dp0

CALL build_package.cmd
CALL db_cmds\db_setup.cmd
CALL run_server.cmd