
## About Git/Hub

send pull request (contributor) :

	git pull-request -m "message" -b develop

apply pull request use git (maintainer) :

	curl https://github.com/isaacselement/ERPWebServer/pull/16.patch | git am
	(you need to close the pull request on web-site manually)

apply pull request use hub (maintainer) :
        (hub browse -- issues)	
	hub am -3 https://github.com/isaacselement/ERPWebServer/pull/16
	(for more information: http://hub.github.com/)


## About SVN
	
Add:

	svn add * --force

Update:
	
	svn update

Commit:
	
	svn commit -m "message"

Delete:
	
	for i in $(svn st | grep \! | awk '{print $2}'); do svn delete $i; done

Delete image's name with '@' character:
	
	for i in $(svn st | grep \! | awk '{print $2}'); do svn delete $i@; done

For missing file name with '@', i.e :

	svn revert /Users/Isaacs/Workspaces/ios_projects/ERP/images/Login/Login@2x.png@
	
Take a look : 

	http://stackoverflow.com/questions/1919859/svn-commit-failing-due-to-missing-file
	http://stackoverflow.com/questions/9601294/svn-commit-file-named-2x-png


## Add .classpath file and change your libraries path in project

	mv classpath .classpath


## Before Launch APP Mysql

	mysql -u***** -p*****
	
	show variables like 'character%';
	
	(For all databases)
	SET character_set_client = utf8 ; 
	SET character_set_server = utf8 ;
	SET character_set_database = utf8 ; 
	
	create database <database_name>;
	show databases;
	use <database_name>;

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
	
	
	
## Document and Convention
	
	"username" and suffix "*User"/"*EmployeeNO" mean the same , equivalent to "employeeNO" 
	"*EmployeeNOs" for many "employeeNO"
	
	["employeeNO", "EmployeeNO", "EmployeeNOs"]
	
	
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

-- 2014-1-21

	alter table WHLendOutOrder drop column notReturnAmount;
	
	
-- 2014-1-17 Note: drop bill first to avoid foreign key constraint exception.
 	
 	drop table SharedReleaseBill;
	drop table SharedReleaseOrder;
	
-- 2014-1-21

	drop table WHLendOutBill;
	drop table WHProductCategory;
	drop table WHMaterialOrder;

-- 2014-2-26

	drop table FinancePettyCashOrder;
	
-- 2014-3-3
	
	alter table EmployeeQuitOrder drop column carryingStuff;
  
-- 2014-3-7

	drop table FinanceCHOrder;
	drop table FinanceSalaryOrder;
	drop table EmployeeBMOrder;
	
-- 2014-3-12

	alter table FinancePaymentOrder drop column biilOrderNOs;
	alter table WHPurchaseBill drop column purchaseQCBill;
	
-- 2013-3-14

	alter table WHPurchaseOrder change payable totalPay float NOT NULL;
	

-- 2013-3-18

	alter table WHInventoryCHOrder drop column totalAmount_N;
	alter table WHInventoryCHOrder drop column amount_N;
	alter table WHInventoryCHOrder drop column lendAmount_N;
	alter table WHInventoryCHOrder drop column priceBasicUnit_N;

-- 2013-3-19
	alter table FinancePaymentBill drop column orderType;
  
-- 2013-3-19

	alter table WHInventoryOrder drop column remainAmount;
	
-- 2013-3-24
	
	drop table FinanceAccountCHOrder;
	alter table FinanceAccount change column category bank varchar(255);
