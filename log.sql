

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
	
-- 2014-3-14

	alter table WHPurchaseOrder change payable totalPay float NOT NULL;
	

-- 2014-3-18

	alter table WHInventoryCHOrder drop column totalAmount_N;
	alter table WHInventoryCHOrder drop column amount_N;
	alter table WHInventoryCHOrder drop column lendAmount_N;
	alter table WHInventoryCHOrder drop column priceBasicUnit_N;

-- 2014-3-19

	alter table FinancePaymentBill drop column orderType;
  
-- 2014-3-19

	alter table WHInventoryOrder drop column remainAmount;
	
-- 2014-3-24
	
	drop table FinanceAccountCHOrder;
	alter table FinanceAccount change column category bank varchar(255);

-- 2014-3-29

	drop table SecurityVisitOrder;
	
-- 2014-3-27
	
	set @DATABASE_NAME = 'ERPWebServer';
	select concat('alter table ', table_name, ' drop column expiredDate;') as sql_statements from information_schema.tables as tb where table_schema = @DATABASE_NAME order by table_name DESC into outfile '/tmp/batch.txt'; 
	source /tmp/batch.txt;
	
-- 2014-3-29
	
	update appsettings set type='ADMIN_APPROVALS' where type='APPROVALS';
	UPDATE APPSETTINGS SET TYPE='USER_JOBPOSITIONS' WHERE TYPE='JOBPOSITIONS';
	
-- 2014-4-3

	update EmployeeCHOrder inner join Employee on EmployeeCHOrder.employeeNO = Employee.employeeNO set EmployeeCHOrder.name = Employee.name ;
	
-- 2014-4-10

	alter table Employee drop column inVisits;
	alter table Employee drop column ownDevice;
	
-- 2014-4-15
	
	select concat(id ,'=',password) from user into outfile '/tmp/properties.txt'; 							-- Save the properties 'cp /tmp/properties.txt ./userDevelop.properties'
	select concat('update User set password=\'', MD5(password), '\' where id=', id, ';') from user into outfile '/tmp/batch1.txt';
	source /tmp/batch1.txt;
	
-- 2014-4-18

	 update EmployeeLeaveOrder inner join Employee on EmployeeLeaveOrder.employeeNO=Employee.employeeNO set EmployeeLeaveOrder.name=Employee.name, EmployeeLeaveOrder.jobTitle=Employee.jobTitle, EmployeeLeaveOrder.department=Employee.department;
	 

-- 2014-4-21

	 update employee set ownApproval=1;
	 
-- 2014-4-22

	update user set id=id-1 where id<=0;
	
-- 2014-4-23
	
	update financesalary set version=1;
	
-- 2014-4-25
	
	delete from User where username in (select tmp.username from ( select username from User left join Employee on username=Employee.employeeNO where Employee.employeeNO is NULL ) tmp) and id > 0;
	delete from Approvals where employeeNO in (select tmp.employeeNO from ( select Approvals.employeeNO from Approvals left join Employee on Approvals.employeeNO=Employee.employeeNO where Employee.employeeNO is NULL ) tmp) ;
	
-- 2014-4-26
	
	alter table employee change urgencyContact urgencyName varchar(255) ;
	
	-- create the function
	drop function if exists split_str;
	create function split_str( x varchar(255), delim varchar(12), pos int) returns varchar(255) return REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos), LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1), delim, '');
	
	-- create the procedure
	drop procedure if exists handleUrgency;
	delimiter //
	create procedure handleUrgency() begin declare done int default false;DECLARE identifer int ; declare a, b, c varchar(255); declare cursor1 CURSOR FOR select id, urgencyName from employee where urgencyName IS NOT NULL; DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE; OPEN cursor1; urgency_loop: LOOP FETCH cursor1 INTO identifer, a; IF done THEN LEAVE urgency_loop; END IF; SET @aname=split_str(a,'.',1); SET @phone=split_str(a,'.',2); SET @relation=split_str(a,'.',3); update employee set urgencyName=@aname, urgencyPhone=@phone, urgencyRelation=@relation where id=identifer; END LOOP; CLOSE cursor1; END;//
	delimiter ;
	call handleUrgency();
	
	-- clean the function and procedure
	drop procedure if exists handleUrgency;
	drop function if exists split_str;
	
	
	alter table employee change education education_experience varchar(255);
	alter table employee change experience work_experience varchar(255);
	
-- 2014-4-29

	update employee set education_experience=replace(education_experience,'-','.');
	
-- 2014-5-08
	
	 alter table employee drop column urgencyRelation;

-- 2014-5-09
	
	 drop table WHInventoryOrder;
	
-- 2014-5-12
	
	alter table employeechorder change name name_O varchar(255);
	
-- 2014-5-14

	alter table WHInventoryOrder drop column supplierDesc;
	
-- 2014-5-21

	alter table WHLendOutOrder drop column remark;
	alter table WHScrapOrder drop column amount;
	
-- 2014-6-2

	alter table WHScrapOrder drop column scrpAmount;

-- 2014-6-2

	update appsettings set type = 'ADMIN_ORDERS_APPROVALS' where type ='ADMIN_APPROVALS';
	update appsettings set type='ADMIN_ORDERS_EXPIRATIONS' WHERE type='ADMIN_ORDERSEXPIRATIONS';
	
-- 2014-6-5

	DROP PROCEDURE IF EXISTS getAllTraceTables;
	delimiter //
	CREATE PROCEDURE getAllTraceTables(kDBName varchar(255)) BEGIN  select ATableName from (select table_name as ATableName from information_schema.columns where table_schema=kDBName and column_name='createDate') as A inner join (select table_name as BTableName from information_schema.columns where table_schema=kDBName and column_name='app1') as B on ATableName = BTableName ORDER BY ATableName;  END;//
	delimiter ;
	-- call getAllTraceTables('erpwebserver');
	
	
	drop procedure if exists getTraceResults;
	delimiter //
	CREATE PROCEDURE getTraceResults(_kDBName varchar(255), _kTableName varchar(255),  _kStartDate DATE, _kCreateUser varchar(255), _kCount INT)  BEGIN  DECLARE _TABLENAME varchar(255);  DECLARE sql_text varchar(255);  DECLARE STARTDATE DATE;  SET _TABLENAME = CONCAT_WS('.',_kDBName,_kTableName);  IF _kCount = 0 THEN SET @sql_text = concat("SELECT id, orderNO FROM ", _TABLENAME , " WHERE createDate >=  '", _kStartDate, "' and createUser = '", _kCreateUser, "'" ) ; ELSE SET @sql_text = concat("SELECT id, orderNO FROM ", _TABLENAME , " WHERE createDate >=  '", _kStartDate, "' and createUser = '", _kCreateUser, "'  LIMIT 0,", _kCount) ; END IF;  PREPARE stmt FROM @sql_text; EXECUTE stmt; DEALLOCATE PREPARE stmt;    END;//
	delimiter ;
	-- call getTraceResults('erpwebserver', 'Employee', '2014-01-25', 0);
	
-- 2014-6-7

	update approvals set unreadapprovals='{}'
	
-- 2014-6-8

	alter table WHLendOutBill drop column billCreateUser;
	alter table WHLendOutOrder drop column notReturnAmount;

-- 2014-6-16 

    rename table WHInventoryOrder to WHInventory;
    
-- 2014-6-18 

    alter table WHPickingOrder drop column app3;
    
-- 2014-6-19 

    alter table WHInventory drop column oneUnit;
    alter table WHRecycleInventory drop column oneUnit;
    alter table WHInventoryCHOrder drop column oneUnit_O;
    alter table WHInventoryCHOrder drop column oneUnit_N;
        
-- 2014-6-20

    alter table WHPickingOrder drop column pickingDate;
    
-- 2014-6-25

	alter table WHInventory drop column productDescPDF;
	alter table WHInventory drop column supplierDesc;
    alter table WHInventoryCHOrder drop column productLocation_O;
    alter table WHInventoryCHOrder drop column productDesc_O;
    alter table WHInventoryCHOrder drop column productDescPDF_O;
    alter table WHInventoryCHOrder drop column productLocation_N;
    alter table WHInventoryCHOrder drop column productDesc_N;
    alter table WHInventoryCHOrder drop column productDescPDF_N;
    
 -- 2014-7-11

	alter table Contract change number clientNumber varchar(255);
	alter table Contract change name clientName varchar(255);
	alter table Contract drop column contact;
	alter table Contract drop column phoneNO;
	alter table Contract drop column faxNO;
    
    