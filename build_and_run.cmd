@ECHO OFF
SETLOCAL
CD /d %~dp0

CALL build_package.cmd
CALL run_server.cmd
