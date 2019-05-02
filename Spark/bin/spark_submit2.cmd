set PYTHONHASHSEED=0

set CLASS=org.apache.spark.deploy.SparkSubmit
"%~dp0spark-class2.cmd" %CLASS% %*
