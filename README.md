# 2019-OntologySystem
Assignment for the 2019 edition of the "Web Development and the Semantic Web" course, by Alexandre Adler Cunha de Freitas and Gustavo Ludovico Guidoni.


BD
  Criar o banco de dados ROS  (Rational Ontology System).
  Criar o usuário dwws e fornecer acesso total ao ROS.


WildFly
  Alterar as seguintes configurações em standalone.xml

  1) Altere a permissão de acesso:
  de   : <default-missing-method-permissions-deny-access value="true"/>
  para : <default-missing-method-permissions-deny-access value="false"/>

  2) Criar o datasource:
  
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
  3) Adicione
  				<security-domain name="rationalontology">
				    <authentication>
				        <login-module code="Database" flag="required">
				            <module-option name="dsJndiName" value="java:jboss/datasources/RationalOntology"/>
				            <module-option name="principalsQuery" value="select password from user where email=?"/>
				            <module-option name="hashAlgorithm" value="MD5"/>
				            <module-option name="hashEncoding" value="base64"/>
				            <module-option name="hashUserPassword" value="true"/>
				        </login-module>
				    </authentication>
				</security-domain>
				
				
				
				<div class="highlight highlight-text-xml"><pre> &lt;<span class="pl-ent">datasource</span> <span class="pl-e">jta</span>=<span class="pl-s"><span class="pl-pds">"</span>true<span class="pl-pds">"</span></span> <span class="pl-e">jndi-name</span>=<span class="pl-s"><span class="pl-pds">"</span>java:/jboss/datasources/PoopTime<span class="pl-pds">"</span></span> <span class="pl-e">pool-name</span>=<span class="pl-s"><span class="pl-pds">"</span>PoopTimePool<span class="pl-pds">"</span></span> <span class="pl-e">enabled</span>=<span class="pl-s"><span class="pl-pds">"</span>true<span class="pl-pds">"</span></span> <span class="pl-e">use-java-context</span>=<span class="pl-s"><span class="pl-pds">"</span>true<span class="pl-pds">"</span></span>&gt;
	&lt;<span class="pl-ent">connection-url</span>&gt;jdbc:mysql://localhost:3306/pooptime&lt;/<span class="pl-ent">connection-url</span>&gt;
	&lt;<span class="pl-ent">driver</span>&gt;mysql&lt;/<span class="pl-ent">driver</span>&gt;
	&lt;<span class="pl-ent">security</span>&gt;
	    &lt;<span class="pl-ent">user-name</span>&gt;dwws&lt;/<span class="pl-ent">user-name</span>&gt;
	    &lt;<span class="pl-ent">password</span>&gt;dwws&lt;/<span class="pl-ent">password</span>&gt;
	&lt;/<span class="pl-ent">security</span>&gt;
 &lt;/<span class="pl-ent">datasource</span>&gt;</pre></div>
