What is PolyBase?
======
PolyBase enables your SQL Server 2016 instance to process Transact-SQL queries that read data from Hadoop. The same query can also access relational tables in your SQL Server. PolyBase enables the same query to also join the data from Hadoop and SQL Server. In SQL Server, an external table or external data source provides the connection to Hadoop.

PolyBase pushes some computations to the Hadoop node to optimize the overall query. However, PolyBase external access is not limited to Hadoop. Other unstructured non-relational tables are also supported, such as delimited text files.

Supported SQL products and services
-----------
PolyBase provides these same functionalities for the following SQL products from Microsoft:

*SQL Server 2016 and later versions (Windows only)

*Analytics Platform System (formerly Parallel Data Warehouse)

*Azure SQL Data Warehouse

Azure integration
-------
With the underlying help of PolyBase, T-SQL queries can also import and export data from Azure Blob Storage. Further, PolyBase enables Azure SQL Data Warehouse to import and export data from Azure Data Lake Store, and from Azure Blob Storage.

