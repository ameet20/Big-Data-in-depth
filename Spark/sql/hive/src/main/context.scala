package org.apache.spark.sql.hive

import org.apache.spark.SparkContext
import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.internal.Logging
import org.apache.spark.sql.{SparkSession, SQLContext}

@deprecated("Use SparkSession.builder.enableHiveSupport instead", "2.0.0")
class HiveContext private[hive](_sparkSession: SparkSession)
  extends SQLContext(_sparkSession) with Logging {

  self =>

  def this(sc: SparkContext) = {
    this(SparkSession.builder().sparkContext(HiveUtils.withHiveExternalCatalog(sc)).getOrCreate())
  }

  def this(sc: JavaSparkContext) = this(sc.sc)
  
  override def newSession(): HiveContext = {
    new HiveContext(sparkSession.newSession())
  }
  
  def refreshTable(tableName: String): Unit = {
    sparkSession.catalog.refreshTable(tableName)
  }

}
