call "%~dp0find-spark-home.cmd"

call "%SPARK_HOME%\bin\load-spark-env.cmd"
set _SPARK_CMD_USAGE=Usage: bin\pyspark.cmd [options]

rem Figure out which Python to use.
if "x%PYSPARK_DRIVER_PYTHON%"=="x" (
  set PYSPARK_DRIVER_PYTHON=python
  if not [%PYSPARK_PYTHON%] == [] set PYSPARK_DRIVER_PYTHON=%PYSPARK_PYTHON%
)

set PYTHONPATH=%SPARK_HOME%\python;%PYTHONPATH%
set PYTHONPATH=%SPARK_HOME%\python\lib\py4j-0.10.8.1-src.zip;%PYTHONPATH%

set OLD_PYTHONSTARTUP=%PYTHONSTARTUP%
set PYTHONSTARTUP=%SPARK_HOME%\python\pyspark\shell.py

call "%SPARK_HOME%\bin\spark-submit2.cmd" pyspark-shell-main --name "PySparkShell" %*
