@echo off
setlocal

rem Set the path to the bin folder containing java.exe
set "java_bin_path=bin"

rem Find the JAR file in the current folder
for %%i in (*.jar) do set "jar_file=%%i"

rem Check if a JAR file was found
if not defined jar_file (
    echo No JAR file found in the current folder.
    exit /b 1
)

rem Build the command to execute the JAR file
set "java_command=%java_bin_path%\java.exe -jar "%jar_file%""

rem Execute the command
%java_command%

endlocal

