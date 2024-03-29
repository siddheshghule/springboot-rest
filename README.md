# Digital Hotel Administration

## Description
Digital Hotel Administration is a backend application,

• Built modern application with Java, Spring Boot, SQL server.

• Developed Web Services using REST Architecture.

• Clean code and architecture to integrate new features.

• Tools/Plugins: MySQL, Swagger, Actuator, JPA, Security, HATEOAS.

To get the structure of the data please refer to swagger-ui, by following steps mentioned in [Running the Application]

### Things to do:
1. Add Unit tests and controller tests
2. Use docker instead of MySQL application.
3. Check loggers
4. Refactoring or implementing clean coding wherever needed. 

### Class Diagram 

<img width="1268" alt="Screenshot 2021-04-16 at 11 32 38 AM" src="https://user-images.githubusercontent.com/50058377/115004859-8dc29a00-9ea7-11eb-84a8-3ab79b149761.png">

### Dependencies used:
    <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework.hateoas/spring-hateoas -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

		<!-- The goal of ModelMapper is to make object mapping easy -->
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>2.3.0</version>
	    </dependency>
    <!-- https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>

## Getting Started

### Prerequisites
0. Java version 1.8
1. IDE (Integrated Development Environment), For eg. Eclipse
2. MYSQL
3. mySQLWorkbench for UI of databases (optional)

### Installing
1. You can use Eclipse IDE, download from https://www.eclipse.org/downloads/, for your OS, and you can opt for Eclipse IDE for Java Developers.
2. Download MYSQL for your OS from https://dev.mysql.com/downloads/mysql/  
    2.1 Remember root user password while installing MYSQL,  
    2.2 Start MYSQL server   
      2.2.1 For MACOS with MYSQL version mysql-8.0.19 , in terminal type following:
    
        'sudo /usr/local/mysql-8.0.19-macos10.15-x86_64/support-files/mysql.server start'

	    If it is taking time to start, then there is an issue, so kill all running mysql, using 'sudo killall mysql', 
	    then change ownership of mysql folder[mysql-8.0.19-macos10.15-x86_64], using command 'sudo chown -R mysql 
        mysql-8.0.19-macos10.15-x86_64',
        
        then start the server again.

        [Note: You can use any version.]

    2.2.2 Go in bin folder to login into root user of MYSQL, using

        'sudo /usr/local/mysql-8.0.19-macos10.15-x86_64/bin/mysql -u root -p '(Return)

        password: (system password, as you are suing sudo command)

        Enter password: (enter MYSQL password provided during installation)

    2.2.3 Once you are in MYSQL as root user, create database, using

	    show databases; // To view list of databases

	    create database digital_hotel; // use digital_hotel as database name as mentioned

    2.2.4 Create a user to work with our database

        create user 'user'@'localhost' identified by 'password';  
        
        (where username=user, password=password)

        grant all privileges on digital_hotel.* to 'user'@'localhost';

        flush privileges;
            
        exit [from root user]
        
    2.2.5 login into new user
        
        sudo /usr/local/mysql-8.0.19-macos10.15-x86_64/bin/mysql -u user -p (Return)
	    
        Enter password:

        
    - For Windows, follows installation steps from this (https://phoenixnap.com/kb/install-mysql-on-windows)

        If facing error on command "C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqld", there are chances that your server is already started after installation and try creating database and user using same steps mentioned for MACOS (2.3-2.5)


3. In mySQLWorkbench, create a new connection by selection Database/Manage Connections...


        Connection Name: digital_hotel

        Connection Method: Standart (TCP/IP)

        Hostname: 127.0.0.1     Port: 3306

        Username: user

        Password: [Store in Keychain ...]

        Password: password

        Test Connection (To check if connection is established)


        Open digital_hotel,

            First command - use digital_hotel;

            [Then you can try othe SQL commands, as for creating tables, it will be created automatically when we run 
            application in IDE.]

## Running the Application

In IDE, import Existing Maven Projects, select application folder and import.
If application contains no errors, 
        Right click on digital-hotel-administration in Project Explorer, 
            Run As -> Java Application -> DigitalHotelManagementApplication [Ok]

The Application is running on 8080 port, so if 8080 is already in use, kill the existing application adn the run again.

To interact with the application, in browser run
http://localhost:8080/digital-hotel-ws/swagger-ui/index.html#/

* [REST API](https://restfulapi.net) - using REST (Representational State Transfer) architecture.
* [Maven](https://maven.apache.org/) - Dependency Management.
* [Swagger](https://swagger.io) - For documentation and test case generation.

## Author

* **Siddhesh Ghule** 
