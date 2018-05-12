call runcrud.bat

start "Chrome" "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
if "%ERRORLEVEL%" == "0" goto startgetTasks
echo.
echo Cannot open chrome
goto fail

:startgetTasks
start "getTasks" "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == 0 goto end
echo Cannot open getTasks
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished