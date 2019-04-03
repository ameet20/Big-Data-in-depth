--Create Schema for External Tables 
CREATE SCHEMA ext;

--Create Master Key. Required to encrypt the credential secret in the next step.
CREATE MASTER KEY;

--Create Database Scoped Credential. Provide the Azure storage account key. 
--The identity name does not affect authentication to Azure storage.
CREATE DATABASE SCOPED CREDENTIAL AzureStorageCredential 
WITH IDENTITY = 'user', 
SECRET = 'azure_storage_account_key_goes_here';

--Create External Data Source. Specify location and credential to access your Azure blob storage.
CREATE EXTERNAL DATA SOURCE AzureStorage 

--Create External File format. Specify the layout of data stored in Azure storage blobs. 
CREATE EXTERNAL FILE FORMAT TextFileFormat 
WITH 
(   
    FORMAT_TYPE = DELIMITEDTEXT, 
    FORMAT_OPTIONS  
    (
        FIELD_TERMINATOR ='|',
        USE_TYPE_DEFAULT = FALSE
    ),
	DATA_COMPRESSION = 'org.apache.hadoop.io.compress.GzipCodec'
);

--DimAccount
CREATE EXTERNAL TABLE ext.DimAccount (
	[AccountKey] [int] NOT NULL,
	[ParentAccountKey] [int] NULL,
	[AccountLabel] [nvarchar](100) NULL,
	[AccountName] [nvarchar](50) NULL,
	[AccountDescription] [nvarchar](50) NULL,
	[AccountType] [nvarchar](50) NULL,
	[Operator] [nvarchar](50) NULL,
	[CustomMembers] [nvarchar](300) NULL,
	[ValueType] [nvarchar](50) NULL,
	[CustomMemberOptions] [nvarchar](200) NULL
)
WITH (
        LOCATION='data/DimAccount.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimAccount 
WITH 
(
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimAccount;

--DimChannel
CREATE EXTERNAL TABLE ext.DimChannel (
	[ChannelKey] [int] NOT NULL,
	[ChannelLabel] [nvarchar](100) NOT NULL,
	[ChannelName] [nvarchar](20) NULL,
	[ChannelDescription] [nvarchar](50) NULL
)
WITH (
        LOCATION='data/DimChannel.zip', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimChannel
WITH 
(
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimChannel;

--DimCurrency
CREATE EXTERNAL TABLE ext.DimCurrency (
	[CurrencyKey] [int] NOT NULL,
	[CurrencyLabel] [nvarchar](10) NOT NULL,
	[CurrencyName] [nvarchar](20) NOT NULL,
	[CurrencyDescription] [nvarchar](50) NOT NULL
)
WITH (
        LOCATION='data/DimCurrency.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimCurrency
WITH 
(
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimCurrency;

--DimCustomer
CREATE EXTERNAL TABLE ext.DimCustomer (
	[CustomerKey] [int] NOT NULL,
	[GeographyKey] [int] NOT NULL,
	[CustomerLabel] [nvarchar](100) NOT NULL,
	[Title] [nvarchar](8) NULL,
	[FirstName] [nvarchar](50) NULL,
	[MiddleName] [nvarchar](50) NULL,
	[LastName] [nvarchar](50) NULL,
	[NameStyle] [bit] NULL,
	[BirthDate] [date] NULL,
	[MaritalStatus] [nchar](1) NULL,
	[Suffix] [nvarchar](10) NULL,
	[Gender] [nvarchar](1) NULL,
	[EmailAddress] [nvarchar](50) NULL,
	[YearlyIncome] [money] NULL,
	[TotalChildren] [tinyint] NULL,
	[NumberChildrenAtHome] [tinyint] NULL,
	[Education] [nvarchar](40) NULL,
	[Occupation] [nvarchar](100) NULL,
	[HouseOwnerFlag] [nchar](1) NULL,
	[NumberCarsOwned] [tinyint] NULL,
	[AddressLine1] [nvarchar](120) NULL,
	[AddressLine2] [nvarchar](120) NULL,
	[Phone] [nvarchar](20) NULL,
	[DateFirstPurchase] [date] NULL,
	[CustomerType] [nvarchar](15) NULL,
	[CompanyName] [nvarchar](100) NULL
)
WITH (
        LOCATION='data/DimCustomer.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimCustomer
WITH 
(	CLUSTERED COLUMNSTORE INDEX, 
	DISTRIBUTION = HASH(CustomerKey)
)
AS
SELECT * FROM ext.DimCustomer; 

--DimDate
CREATE EXTERNAL TABLE ext.DimDate (
	[Datekey] [datetime] NOT NULL,
	[FullDateLabel] [nvarchar](20) NOT NULL,
	[DateDescription] [nvarchar](20) NOT NULL,
	[CalendarYear] [int] NOT NULL,
	[CalendarYearLabel] [nvarchar](20) NOT NULL,
	[CalendarHalfYear] [int] NOT NULL,
	[CalendarHalfYearLabel] [nvarchar](20) NOT NULL,
	[CalendarQuarter] [int] NOT NULL,
	[CalendarQuarterLabel] [nvarchar](20) NULL,
	[CalendarMonth] [int] NOT NULL,
	[CalendarMonthLabel] [nvarchar](20) NOT NULL,
	[CalendarWeek] [int] NOT NULL,
	[CalendarWeekLabel] [nvarchar](20) NOT NULL,
	[CalendarDayOfWeek] [int] NOT NULL,
	[CalendarDayOfWeekLabel] [nvarchar](10) NOT NULL,
	[FiscalYear] [int] NOT NULL,
	[FiscalYearLabel] [nvarchar](20) NOT NULL,
	[FiscalHalfYear] [int] NOT NULL,
	[FiscalHalfYearLabel] [nvarchar](20) NOT NULL,
	[FiscalQuarter] [int] NOT NULL,
	[FiscalQuarterLabel] [nvarchar](20) NOT NULL,
	[FiscalMonth] [int] NOT NULL,
	[FiscalMonthLabel] [nvarchar](20) NOT NULL,
	[IsWorkDay] [nvarchar](20) NOT NULL,
	[IsHoliday] [int] NOT NULL,
	[HolidayName] [nvarchar](20) NOT NULL,
	[EuropeSeason] [nvarchar](50) NULL,
	[NorthAmericaSeason] [nvarchar](50) NULL,
	[AsiaSeason] [nvarchar](50) NULL
)
WITH (
        LOCATION='data/DimDate.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimDate
WITH 
(
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimDate;

--DimEmployee
CREATE EXTERNAL TABLE ext.DimEmployee (
	[EmployeeKey] [int] NOT NULL,
	[ParentEmployeeKey] [int] NULL,
	[FirstName] [nvarchar](50) NOT NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[MiddleName] [nvarchar](50) NULL,
	[Title] [nvarchar](50) NULL,
	[HireDate] [date] NULL,
	[BirthDate] [date] NULL,
	[EmailAddress] [nvarchar](50) NULL,
	[Phone] [nvarchar](25) NULL,
	[MaritalStatus] [nchar](1) NULL,
	[EmergencyContactName] [nvarchar](50) NULL,
	[EmergencyContactPhone] [nvarchar](25) NULL,
	[SalariedFlag] [bit] NULL,
	[Gender] [nchar](1) NULL,
	[PayFrequency] [tinyint] NULL,
	[BaseRate] [money] NULL,
	[VacationHours] [smallint] NULL,
	[CurrentFlag] [bit] NOT NULL,
	[SalesPersonFlag] [bit] NOT NULL,
	[DepartmentName] [nvarchar](50) NULL,
	[StartDate] [date] NULL,
	[EndDate] [date] NULL,
	[Status] [nvarchar](50) NULL
)
WITH (
        LOCATION='data/DimEmployee.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimEmployee
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimEmployee;

--DimEntity
CREATE EXTERNAL TABLE ext.DimEntity (
	[EntityKey] [int] NOT NULL,
	[EntityLabel] [nvarchar](100) NULL,
	[ParentEntityKey] [int] NULL,
	[ParentEntityLabel] [nvarchar](100) NULL,
	[EntityName] [nvarchar](50) NULL,
	[EntityDescription] [nvarchar](100) NULL,
	[EntityType] [nvarchar](100) NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[Status] [nvarchar](50) NULL
)
WITH (
        LOCATION='data/DimEntity.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimEntity
WITH 
(
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimEntity;

--DimGeography
CREATE EXTERNAL TABLE ext.DimGeography (
	[GeographyKey] [int] NOT NULL,
	[GeographyType] [nvarchar](50) NOT NULL,
	[ContinentName] [nvarchar](50) NOT NULL,
	[CityName] [nvarchar](100) NULL,
	[StateProvinceName] [nvarchar](100) NULL,
	[RegionCountryName] [nvarchar](100) NULL
)
WITH (
        LOCATION='data/DimGeography.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimGeography
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimGeography;

--DimMachine
CREATE EXTERNAL TABLE ext.DimMachine (
	[MachineKey] [int] NOT NULL,
	[MachineLabel] [nvarchar](100) NULL,
	[StoreKey] [int] NOT NULL,
	[MachineType] [nvarchar](50) NOT NULL,
	[MachineName] [nvarchar](100) NOT NULL,
	[MachineDescription] [nvarchar](200) NOT NULL,
	[VendorName] [nvarchar](50) NOT NULL,
	[MachineOS] [nvarchar](50) NOT NULL,
	[MachineSource] [nvarchar](100) NOT NULL,
	[MachineHardware] [nvarchar](100) NULL,
	[MachineSoftware] [nvarchar](100) NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
	[ServiceStartDate] [datetime] NOT NULL,
	[DecommissionDate] [datetime] NULL,
	[LastModifiedDate] [datetime] NULL
)
WITH (
        LOCATION='data/DimMachine.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimMachine
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimMachine;

--DimOutage
CREATE EXTERNAL TABLE ext.DimOutage (
	[OutageKey] [int] NOT NULL,
	[OutageLabel] [nvarchar](100) NOT NULL,
	[OutageName] [nvarchar](50) NOT NULL,
	[OutageDescription] [nvarchar](200) NOT NULL,
	[OutageType] [nvarchar](50) NOT NULL,
	[OutageTypeDescription] [nvarchar](200) NOT NULL,
	[OutageSubType] [nvarchar](50) NOT NULL,
	[OutageSubTypeDescription] [nvarchar](200) NOT NULL
)
WITH (
        LOCATION='data/DimOutage.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimOutage
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimOutage;

--DimProduct
CREATE EXTERNAL TABLE ext.DimProduct (
	[ProductKey] [int] NOT NULL,
	[ProductLabel] [nvarchar](255) NULL,
	[ProductName] [nvarchar](500) NULL,
	[ProductDescription] [nvarchar](400) NULL,
	[ProductSubcategoryKey] [int] NULL,
	[Manufacturer] [nvarchar](50) NULL,
	[BrandName] [nvarchar](50) NULL,
	[ClassID] [nvarchar](10) NULL,
	[ClassName] [nvarchar](20) NULL,
	[StyleID] [nvarchar](10) NULL,
	[StyleName] [nvarchar](20) NULL,
	[ColorID] [nvarchar](10) NULL,
	[ColorName] [nvarchar](20) NOT NULL,
	[Size] [nvarchar](50) NULL,
	[SizeRange] [nvarchar](50) NULL,
	[SizeUnitMeasureID] [nvarchar](20) NULL,
	[Weight] [float] NULL,
	[WeightUnitMeasureID] [nvarchar](20) NULL,
	[UnitOfMeasureID] [nvarchar](10) NULL,
	[UnitOfMeasureName] [nvarchar](40) NULL,
	[StockTypeID] [nvarchar](10) NULL,
	[StockTypeName] [nvarchar](40) NULL,
	[UnitCost] [money] NULL,
	[UnitPrice] [money] NULL,
	[AvailableForSaleDate] [datetime] NULL,
	[StopSaleDate] [datetime] NULL,
	[Status] [nvarchar](7) NULL,
	[ImageURL] [nvarchar](150) NULL,
	[ProductURL] [nvarchar](150) NULL
)
WITH (
        LOCATION='data/DimProduct.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimProduct
WITH 
(
	CLUSTERED COLUMNSTORE INDEX, 
	DISTRIBUTION = HASH(ProductKey)
)
AS
SELECT * FROM ext.DimProduct;

--DimProductCategory
CREATE EXTERNAL TABLE ext.DimProductCategory (
	[ProductCategoryKey] [int] NOT NULL,
	[ProductCategoryLabel] [nvarchar](100) NULL,
	[ProductCategoryName] [nvarchar](30) NOT NULL,
	[ProductCategoryDescription] [nvarchar](50) NOT NULL
)
WITH (
        LOCATION='data/DimProductCategory.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimProductCategory
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimProductCategory;

--DimProductSubcategory
CREATE EXTERNAL TABLE ext.DimProductSubcategory (
	[ProductSubcategoryKey] [int] NOT NULL,
	[ProductSubcategoryLabel] [nvarchar](100) NULL,
	[ProductSubcategoryName] [nvarchar](50) NOT NULL,
	[ProductSubcategoryDescription] [nvarchar](100) NULL,
	[ProductCategoryKey] [int] NULL
)
WITH (
        LOCATION='data/DimProductSubcategory.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimProductSubcategory
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimProductSubcategory;

--DimPromotion
CREATE EXTERNAL TABLE ext.DimPromotion (
	[PromotionKey] [int] NOT NULL,
	[PromotionLabel] [nvarchar](100) NULL,
	[PromotionName] [nvarchar](100) NULL,
	[PromotionDescription] [nvarchar](255) NULL,
	[DiscountPercent] [float] NULL,
	[PromotionType] [nvarchar](50) NULL,
	[PromotionCategory] [nvarchar](50) NULL,
	[StartDate] [datetime] NOT NULL,
	[EndDate] [datetime] NULL,
	[MinQuantity] [int] NULL,
	[MaxQuantity] [int] NULL
)
WITH (
        LOCATION='data/DimPromotion.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimPromotion
WITH 
(
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimPromotion;

--DimSalesTerritory
CREATE EXTERNAL TABLE ext.DimSalesTerritory (
	[SalesTerritoryKey] [int] NOT NULL,
	[GeographyKey] [int] NOT NULL,
	[SalesTerritoryLabel] [nvarchar](100) NULL,
	[SalesTerritoryName] [nvarchar](50) NOT NULL,
	[SalesTerritoryRegion] [nvarchar](50) NOT NULL,
	[SalesTerritoryCountry] [nvarchar](50) NOT NULL,
	[SalesTerritoryGroup] [nvarchar](50) NULL,
	[SalesTerritoryLevel] [nvarchar](10) NULL,
	[SalesTerritoryManager] [int] NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[Status] [nvarchar](50) NULL
)
WITH (
        LOCATION='data/DimSalesTerritory.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimSalesTerritory
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimSalesTerritory;

--DimScenario
CREATE EXTERNAL TABLE ext.DimScenario (
	[ScenarioKey] [int] NOT NULL,
	[ScenarioLabel] [nvarchar](100) NOT NULL,
	[ScenarioName] [nvarchar](20) NULL,
	[ScenarioDescription] [nvarchar](50) NULL
)
WITH (
        LOCATION='data/DimScenario.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimScenario
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimScenario;

--DimStore
CREATE EXTERNAL TABLE ext.DimStore (
	[StoreKey] [int] NOT NULL,
	[GeographyKey] [int] NOT NULL,
	[StoreManager] [int] NULL,
	[StoreType] [nvarchar](15) NULL,
	[StoreName] [nvarchar](100) NOT NULL,
	[StoreDescription] [nvarchar](300) NOT NULL,
	[Status] [nvarchar](20) NOT NULL,
	[OpenDate] [datetime] NOT NULL,
	[CloseDate] [datetime] NULL,
	[EntityKey] [int] NULL,
	[ZipCode] [nvarchar](20) NULL,
	[ZipCodeExtension] [nvarchar](10) NULL,
	[StorePhone] [nvarchar](15) NULL,
	[StoreFax] [nvarchar](14) NULL,
	[AddressLine1] [nvarchar](100) NULL,
	[AddressLine2] [nvarchar](100) NULL,
	[CloseReason] [nvarchar](20) NULL,
	[EmployeeCount] [int] NULL,
	[SellingAreaSize] [float] NULL,
	[LastRemodelDate] [datetime] NULL
)
WITH (
        LOCATION='data/DimStore.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.DimStore
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.DimStore;

--FactExchangeRate
CREATE EXTERNAL TABLE ext.FactExchangeRate (
	[ExchangeRateKey] [int] NOT NULL,
	[CurrencyKey] [int] NOT NULL,
	[DateKey] [datetime] NOT NULL,
	[AverageRate] [float] NOT NULL,
	[EndOfDayRate] [float] NOT NULL
)
WITH (
        LOCATION='data/FactExchangeRate.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactExchangeRate
WITH 
(
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.FactExchangeRate;

--FactInventory
CREATE EXTERNAL TABLE ext.FactInventory (
	[InventoryKey] [int] NOT NULL,
	[DateKey] [datetime] NOT NULL,
	[StoreKey] [int] NOT NULL,
	[ProductKey] [int] NOT NULL,
	[CurrencyKey] [int] NOT NULL,
	[OnHandQuantity] [int] NOT NULL,
	[OnOrderQuantity] [int] NOT NULL,
	[SafetyStockQuantity] [int] NULL,
	[UnitCost] [money] NOT NULL,
	[DaysInStock] [int] NULL,
	[MinDayInStock] [int] NULL,
	[MaxDayInStock] [int] NULL,
	[Aging] [int] NULL
)
WITH (
        LOCATION='data/FactInventory.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactInventory
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX, 
	DISTRIBUTION = HASH(InventoryKey)
)
AS
SELECT * FROM ext.FactInventory;

--FactITMachine
CREATE EXTERNAL TABLE ext.FactITMachine2 (
	[ITMachinekey] [int] NOT NULL,
	[MachineKey] [int] NOT NULL,
	[Datekey] [datetime] NOT NULL,
	[CostAmount] [money] NULL,
	[CostType] [nvarchar](200) NOT NULL
)
WITH (
        LOCATION='data/FactITMachine.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactITMachine
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN

)
AS
SELECT * FROM ext.FactITMachine;

--FactITSLA
CREATE EXTERNAL TABLE ext.FactITSLA (
	[ITSLAkey] [int] NOT NULL,
	[DateKey] [datetime] NOT NULL,
	[StoreKey] [int] NOT NULL,
	[MachineKey] [int] NOT NULL,
	[OutageKey] [int] NOT NULL,
	[OutageStartTime] [datetime] NOT NULL,
	[OutageEndTime] [datetime] NOT NULL,
	[DownTime] [int] NOT NULL
)
WITH (
        LOCATION='data/FactITSLA.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactITSLA
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX,
	DISTRIBUTION = ROUND_ROBIN
)
AS
SELECT * FROM ext.FactITSLA;

--FactOnlineSales
CREATE EXTERNAL TABLE ext.FactOnlineSales (
	[OnlineSalesKey] [int] NOT NULL,
	[DateKey] [datetime] NOT NULL,
	[StoreKey] [int] NOT NULL,
	[ProductKey] [int] NOT NULL,
	[PromotionKey] [int] NOT NULL,
	[CurrencyKey] [int] NOT NULL,
	[CustomerKey] [int] NOT NULL,
	[SalesOrderNumber] [nvarchar](20) NOT NULL,
	[SalesOrderLineNumber] [int] NULL,
	[SalesQuantity] [int] NOT NULL,
	[SalesAmount] [money] NOT NULL,
	[ReturnQuantity] [int] NOT NULL,
	[ReturnAmount] [money] NULL,
	[DiscountQuantity] [int] NULL,
	[DiscountAmount] [money] NULL,
	[TotalCost] [money] NOT NULL,
	[UnitCost] [money] NULL,
	[UnitPrice] [money] NULL
)
WITH (
        LOCATION='data/FactOnlineSales.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactOnlineSales
WITH 
(
	CLUSTERED COLUMNSTORE INDEX, 
	DISTRIBUTION = HASH(OnlineSalesKey),
	PARTITION(DateKey RANGE RIGHT FOR VALUES (	'2005-01-01','2006-01-01','2007-01-01','2008-01-01','2009-01-01',
												'2010-01-01','2011-01-01','2012-01-01','2013-01-01','2014-01-01',
												'2015-01-01','2016-01-01','2017-01-01','2018-01-01','2019-01-01','2020-01-01'))
)
AS
SELECT * FROM ext.FactOnlineSales;

--FactSales
CREATE EXTERNAL TABLE ext.FactSales (
	[SalesKey] [int] NOT NULL,
	[DateKey] [datetime] NOT NULL,
	[channelKey] [int] NOT NULL,
	[StoreKey] [int] NOT NULL,
	[ProductKey] [int] NOT NULL,
	[PromotionKey] [int] NOT NULL,
	[CurrencyKey] [int] NOT NULL,
	[UnitCost] [money] NOT NULL,
	[UnitPrice] [money] NOT NULL,
	[SalesQuantity] [int] NOT NULL,
	[ReturnQuantity] [int] NOT NULL,
	[ReturnAmount] [money] NULL,
	[DiscountQuantity] [int] NULL,
	[DiscountAmount] [money] NULL,
	[TotalCost] [money] NOT NULL,
	[SalesAmount] [money] NOT NULL
)
WITH (
        LOCATION='data/FactSales.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactSales
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX, 
	DISTRIBUTION = HASH(SalesKey)
)
AS
SELECT * FROM ext.FactSales;

--FactSalesQuota
CREATE EXTERNAL TABLE ext.FactSalesQuota (
	[SalesQuotaKey] [int] NOT NULL,
	[ChannelKey] [int] NOT NULL,
	[StoreKey] [int] NOT NULL,
	[ProductKey] [int] NOT NULL,
	[DateKey] [datetime] NOT NULL,
	[CurrencyKey] [int] NOT NULL,
	[ScenarioKey] [int] NOT NULL,
	[SalesQuantityQuota] [money] NOT NULL,
	[SalesAmountQuota] [money] NOT NULL,
	[GrossMarginQuota] [money] NOT NULL
)
WITH (
        LOCATION='data/FactSalesQuota.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactSalesQuota
WITH 
(
	CLUSTERED COLUMNSTORE INDEX, 
	DISTRIBUTION = HASH(SalesQuotaKey)
)
AS
SELECT * FROM ext.FactSalesQuota;

--FactStrategyPlan
CREATE EXTERNAL TABLE ext.FactStrategyPlan (
	[StrategyPlanKey] [int] NOT NULL,
	[Datekey] [datetime] NOT NULL,
	[EntityKey] [int] NOT NULL,
	[ScenarioKey] [int] NOT NULL,
	[AccountKey] [int] NOT NULL,
	[CurrencyKey] [int] NOT NULL,
	[ProductCategoryKey] [int] NULL,
	[Amount] [money] NOT NULL
)
WITH (
        LOCATION='data/FactStrategyPlan.txt.gz', 
        DATA_SOURCE=AzureStorage, 
        FILE_FORMAT=TextFileFormat
);

CREATE TABLE dbo.FactStrategyPlan
WITH 
(	
	CLUSTERED COLUMNSTORE INDEX, 
	DISTRIBUTION = HASH(StrategyPlanKey)
)
AS
SELECT * FROM ext.FactStrategyPlan;
