# Rational Ontology System
Assignment for the 2019 edition of the "Web Development and the Semantic Web" course, by Alexandre and Gustavo Ludovico Guidoni

### How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. Install [2019-06 (Version 4.12.0 or higher)](http://www.eclipse.org/);

2. Install [WildFly 17.x or higher](http://wildfly.org) and create a Server configuration within Eclipse;

3. Install [MySQL](http://www.mysql.com/products/community/) and create a schema called `ros` and a user called `dwws` with password `dwws` and full access to the `ros` database;

4. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

5. Configure the datasource in WildFly's `standalone.xml` file:

5.1 Database Access:
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

5.2 Object Access Permissions:
```XML
	Altere de: <default-missing-method-permissions-deny-access value="true"/>
	    para : <default-missing-method-permissions-deny-access value="false"/>
```

5.3 JAAS Configuration (Authentication and Authorization)
```XML
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


6. In Eclipse, use _File_ > _Import_ > _Git_ > _Projects from Git_ to import the Eclipse project existing in this repository;

7. You might have to adjust the server settings in the imported project: right-click the _rationalontology_ project and select _Properties_. In the _Server_ section, select the _WildFly 17.x_ server. In the _Targeted Runtimes_ section, select the _WildFly 17.x Runtime_;

## Authors

* **Alexandre**
* **Gustavo Ludovico Guidoni**
