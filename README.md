

## Add .classpath file and change your libraries path in project

	mv classpath .classpath




## Mysql

	mysql -u***** -p*****

	show databases;

	use <database_name>;

	SET character_set_client = utf8 ; 

	show tables;

	DESCRIBE User;
	
	ALTER TABLE User CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
	
	ALTER TABLE User MODIFY COLUMN permissions Text;
	
	
	
	
## Initialize Data In Mysql 
	
	INSERT INTO `ERPWebServer`.`User` (`username`) VALUES ('administrator');
	UPDATE `ERPWebServer`.`User` SET `id`='0' WHERE `username`='administrator';
	INSERT INTO `ERPWebServer`.`Approvals` (`username`) VALUES ('administrator');
	
	
	
	
	
2013-11-11

	alter table Employee alter jobLevel set default 10;
	alter table BusinessClient add unique (clientNO);
