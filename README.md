
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
	show variables like 'wait_timeout';
	set wait_timeout=7200;
	
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
	
	

	
	
	