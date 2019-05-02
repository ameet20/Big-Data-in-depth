call "%~dp0find-spark-home.cmd"

set _SPARK_CMD_USAGE=Usage: .\bin\spark-sql [options] [cli option]

call "%SPARK_HOME%\bin\spark-submit2.cmd" --class org.apache.spark.sql.hive.thriftserver.SparkSQLCLIDriver %*
