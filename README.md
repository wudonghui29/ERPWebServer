

## Add .classpath file and change your libraries path in project

	mv classpath .classpath


## Before Launch APP Mysql

	mysql -u***** -p*****
	
	(For all databases)
	SET character_set_client = utf8 ; 
	SET character_set_server = utf8 ;
	SET character_set_database = utf8 ; 
	
	create database <database_name>;
	
	show databases;

	use <database_name>;
	
	show variables like 'character%';

	(alter database <database_name> character set utf8 ;)

	DELETE FROM mysql.user WHERE User='';
	UPDATE mysql.user SET Password = PASSWORD('test123') WHERE User = 'root';
	FLUSH PRIVILEGES;

## After Launch APP Mysql

	mysql -u***** -p*****

	show databases;

	use <database_name>;

	show tables;

	DESCRIBE User;
	
	SHOW CREATE TABLE User;
	
	SHOW FULL COLUMNS FROM User;
	
	ALTER TABLE User CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
	
	ALTER TABLE User MODIFY COLUMN permissions Text;
	
	
	
	
## Initialize Data In Mysql 
	
	INSERT INTO `ERPWebServer`.`User` (`username`) VALUES ('xinyuanTMD');
	UPDATE `ERPWebServer`.`User` SET `id`='0' WHERE `username`='xinyuanTMD';
	UPDATE `ERPWebServer`.`User` SET `password`='1234' WHERE `id`='0';
	UPDATE `ERPWebServer`.`User` SET `permissions`='{}' WHERE `id`='0';
	INSERT INTO `ERPWebServer`.`Approvals` (`username`) VALUES ('xinyuanTMD');
	
	
	
	
	
-- 2013-11-11
	
	alter table Employee alter jobLevel set default 10;
	alter table BusinessClient add unique (clientNO);
	
	
-- 2013-11-22

	drop table EmployeeSDOrder;
	drop table WHMaterialBill;
	rename table BusinessClient to Client;


-- 2013-11-28

	alter table approvals change username employeeNO varchar(255);


-- 2013-12-06
	
	DROP TABLE DEPARTMENTS;
	
	
-- 2013-12-06

	alter table approvals drop column apnsToken;
	
	
-- 2013-12-06

	alter table employee drop column levelApp_1, drop column levelApp_2, drop column levelApp_3, drop column levelApp_4, drop column levelApp_5;
	alter table employee change departmentName department varchar(255), change subDepartmentName subDepartment varchar(255);
	
	alter table [table_name] change levelApp_1 app1 varchar(255), change levelApp_2 app2 varchar(255), change levelApp_3 app3 varchar(255), change levelApp_4 app4 varchar(255);
	
	
-- 2013-12-24

	rename table EmployeeDormitoryOrder to EmployeeDormOrder;


-- 2013-12-27

	alter table Employee alter inVisits set default false;
	alter table employee drop inVisitList, drop drivingLicence;
	
	
-- 2013-12-30

	drop table APPOrderAttributes;	

-- 2014-1-5

	drop table WHLendOutBill;
	drop table WHLendOutOrder;
	drop table WHInventoryOrder;
	drop table WHScrapOrder;
   
-- 2013-1-13

	alter table employee drop column photopath;
	
	
-- 2013-1-15

	alter table employee drop column orderNO, drop expiredDate, drop exception;

-- 2014-1-16

	drop table WHLendOutBill;
	drop table WHLendOutOrder;
	drop table WHInventoryOrder;
	drop table WHScrapOrder;
   
-- 2014-1-17

	drop table Client;
	

## Document and Convention
	
	"username" and suffix "*User"/"*EmployeeNO" mean the same , equivalent to "employeeNO" 
	"*EmployeeNOs" for many "employeeNO"
	
	["employeeNO", "EmployeeNO", "EmployeeNOs"]
