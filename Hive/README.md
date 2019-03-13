BIG DATA - APACHE HIVE
===========
The Big Data - Apache Hive data warehouse software facilitates reading, writing, and managing large datasets residing in distributed storage using SQL. Built on top of Apache Hadoop (TM), it provides:


  =Tools to enable easy access to data via SQL, thus enabling data warehousing tasks such as extract/transform/load (ETL), 
    reporting, and data analysis

  =A mechanism to impose structure on a variety of data formats

  =Access to files stored either directly in Apache HDFS or in other data storage systems such as Apache HBase

  =Query execution using Apache Hadoop MapReduce, Apache Tez or Apache Spark frameworks.
  
  Hive provides standard SQL functionality, including many of the later 2003 and 2011 features for analytics. These include OLAP functions, subqueries, common table expressions, and more. Hive's SQL can also be extended with user code via user defined functions (UDFs), user defined aggregates (UDAFs), and user defined table functions (UDTFs).
  
  Hive users have a choice of 3 runtimes when executing SQL queries. Users can choose between Apache Hadoop MapReduce, Apache Tez or Apache Spark frameworks as their execution backend. MapReduce is a mature framework that is proven at large scales. However, MapReduce is a purely batch framework, and queries using it may experience higher latencies (tens of seconds), even over small datasets. Apache Tez is designed for interactive query, and has substantially reduced overheads versus MapReduce. Apache Spark is a cluster computing framework that's built outside of MapReduce, but on top of HDFS, with a notion of composable and transformable distributed collection of items called Resilient Distributed Dataset (RDD) which allows processing and analysis without traditional intermediate stages that MapReduce introduces.
  
  Users are free to switch back and forth between these frameworks at any time. In each case, Hive is best suited for use cases where the amount of data processed is large enough to require a distributed system.
  
  Hive is not designed for online transaction processing. It is best used for traditional data warehousing tasks. Hive is designed to maximize scalability (scale out with more machines added dynamically to the Hadoop cluster), performance, extensibility, fault-tolerance, and loose-coupling with its input formats.
  
