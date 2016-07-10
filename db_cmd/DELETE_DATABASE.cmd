@ECHO OFF
SETLOCAL
CD /d %~dp0

CALL db_delete_all_objects.cmd
CALL db_dump_database.cmd
