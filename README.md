# Rational Ontology System
Assignment for the 2019 edition of the "Web Development and the Semantic Web" course, developed by:
* **Alexandre**
* **Gustavo Ludovico Guidoni**

## How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. make sure java 8 or higher is installed. At the command prompt type 'java -jar';

2. Install [Eclipse 2019-06 (Version 4.12.0 or higher)](http://www.eclipse.org/);

3. Install [WildFly 17.x or higher](http://wildfly.org) and create a Server configuration within Eclipse;

4. Install [MySQL](http://www.mysql.com/products/community/) and create a schema called `ros` and a user called `dwws` with password `dwws` and full access to the `ros` database;

5. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

6. Configure the datasource in WildFly's `standalone.xml` file;

6.1 Database Access:
```XML
  	<datasource jta="true"
		jndi-name="java:jboss/datasources/RationalOntology"
		pool-name="RationalOntologyPool" enabled="true"
		use-java-context="true">
		<connection-url>jdbc:mysql://localhost:3306/ros?serverTimezone=UTC</connection-url>
		<driver>mysql</driver>
		<security>
			<user-name>dwws</user-name>
			<password>dwws</password>
		</security>
	</datasource>
```

6.2 Object Access Permissions:
```XML
	Altere de: <default-missing-method-permissions-deny-access value="true"/>
	    para : <default-missing-method-permissions-deny-access value="false"/>
```

6.3 JAAS Configuration (Authentication and Authorization)
```XML
search for <security-domains> in <subsystem xmlns="urn:jboss:domain:security:x.x"> tag (x.x is the version), and add:
	
	<security-domain name="RationalOntology">
		<authentication>
			<login-module code="Database" flag="required">
				<module-option name="dsJndiName" value="java:jboss/datasources/RationalOntology"/>
				<module-option name="principalsQuery" value="select password from user where email=?"/>
				<module-option name="rolesQuery" value="select 'SysAdmin', 'Roles' from user where email=?"/>
				<module-option name="hashAlgorithm" value="MD5"/>
				<module-option name="hashEncoding" value="base64"/>
				<module-option name="hashUserPassword" value="true"/>
			</login-module>
		</authentication>
	</security-domain>
```


7. In Eclipse, use _File_ > _Import_ > _Git_ > _Projects from Git_ to import the Eclipse project existing in this repository;

8. You might have to adjust the server settings in the imported project: right-click the _rationalontology_ project and select _Properties_. In the _Server_ section, select the _WildFly 17.x_ server. In the _Targeted Runtimes_ section, select the _WildFly 17.x Runtime_;

## Main contributions of this work:
1. All interfaces were developed without using JBOSS CrudControlle;

2. All interfaces filter by the logged in user;

3. There are examples of how to implement `join`, `exists`, `in`, `equal`, and `notEqual` through `CriteriaBuilder` queries;

4. Sending email through GMail's server;

5. Sends a `form` to the printer;

6. To see the queries that MySql performs:

6.1 With the root user, type the command `SET GLOBAL general_log = 'ON'`;

6.2 Search for the `MySql DESKTOP-7GMHQFV.log` log file (this file name may vary depending on the version installed). It is usualy in `C:\ProgramData\MySQL\MySQL Server 8.0\Data\`

6.3 After checking the query, disable logging using the `SET GLOBAL general_log = 'OFF'` command.

NOTE: Viewing queries executed by the server consumes relevant database resources in addition to the size of this file grows quickly. So, remember to disable it.
