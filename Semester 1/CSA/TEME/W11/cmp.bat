@echo off
setlocal enableExtensions disableDelayedExpansion

echo.
echo.
echo Da-te mare, Endru skkkrrr
echo.
echo.
echo Note that this batch works for only 6 files as parameters

echo Command line: %0 %*
echo.
echo -[ FILES TO BUILD ]-----------------------
echo.
::if not "%1"=="" echo File 1: "%~nx1"
::if not "%2"=="" echo File 1: "%~nx2"

for %%a in ("%~1" "%~2" "%~3" "%~4" "%~5" "%~6") do if not "%%~fa"=="" echo "%%~nxa"

for %%a in ("%~1" "%~2" "%~3" "%~4" "%~5" "%~6") do if not "%%~fa"=="" nasm.exe -f obj %%~nxa

echo.
echo --------------------------------------


echo Directory:
cd
echo.
echo.
echo Generating %~n1.exe ...
echo.

nasm.exe -f obj %~nx1
nasm.exe -f obj %~nx2
alink.exe %~n1.obj %~n2.obj -oPE -subsys console -entry start

pause

endlocal
goto :eof