# Spring Boot w/ Mybatis User Management System
- Built using ```Maven(3.6.1)```
    ``````
    ~bash $ mvn --version
    Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-05T03:00:29+08:00)
    Maven home: /Users/eloisa/opt/apache-maven
    Java version: 1.8.0_181, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre
    Default locale: en_CA, platform encoding: UTF-8
    OS name: "mac os x", version: "10.14.4", arch: "x86_64", family: "mac"
    ``````
- Spring Boot ```(Version=2.1.5.RELEASE)``` \
    Excerpt from ```pom.xml```:
    ``````
    <parent>
      	<groupId>org.springframework.boot</groupId>  
      	<artifactId>spring-boot-starter-parent</artifactId>  
      	<version>2.1.5.RELEASE</version>  
   		<relativePath/>
    </parent>
    ``````
- Java JDK 1.8
    ``````
    <properties>
    	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
    	<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>  
   		<java.version>1.8</java.version>  
    </properties>
    ``````
- Database dialect used: MySQL@5.7
    - Follow ```src->main->resources->init.sql``` for the layout of the database
- Web front-end: swagger bootstrap, available here: https://github.com/xiaoymin/swagger-bootstrap-ui
