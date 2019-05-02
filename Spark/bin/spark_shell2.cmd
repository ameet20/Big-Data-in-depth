call "%~dp0find-spark-home.cmd"

set LF=^


rem two empty lines are required
set _SPARK_CMD_USAGE=Usage: .\bin\spark-shell.cmd [options]^%LF%%LF%^%LF%%LF%^
Scala REPL options:^%LF%%LF%^
  -I ^<file^>                   preload ^<file^>, enforcing line-by-line interpretation

rem SPARK-4161: scala does not assume use of the java classpath,
rem so we need to add the "-Dscala.usejavacp=true" flag manually. We
rem do this specifically for the Spark shell because the scala REPL
rem has its own class loader, and any additional classpath specified
rem through spark.driver.extraClassPath is not automatically propagated.
if "x%SPARK_SUBMIT_OPTS%"=="x" (
  set SPARK_SUBMIT_OPTS=-Dscala.usejavacp=true
  goto run_shell
)
set SPARK_SUBMIT_OPTS="%SPARK_SUBMIT_OPTS% -Dscala.usejavacp=true"

:run_shell
"%SPARK_HOME%\bin\spark-submit2.cmd" --class org.apache.spark.repl.Main --name "Spark shell" %*
