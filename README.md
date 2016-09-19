[![Build Status](https://travis-ci.org/michelrisucci/spring-jsf-jpa-quickstart.svg?branch=master)](https://travis-ci.org/michelrisucci/spring-jsf-jpa-quickstart) [![Latest Stable Release](https://img.shields.io/badge/stable-2.0.1.GA-blue.svg)](https://github.com/michelrisucci/spring-jsf-jpa-quickstart/releases/tag/2.0.1.GA)

Spring + JSF + JPA Quickstart
=============================

- Version 2.0.1.GA

- What it is: a quickstart project containing initial structure to:
  - ZERO CONFIGURATIONS to run the project! Checkout and run!
    - Web Application Security Access:
      - Username: **`admin`**
      - Password: **`123456`**
  - Spring Context 4.3 as Context Provider;
  - Spring Security 4.1 as Web Security Provider;
  - Jetty 9.3 as Webserver;
  - Pure JavaServer Faces 2.2 (no Primefaces required);
  - Built-in H2, PostgreSQL and MySQL Server connection for JDBC/JPA (extensible to ANY database - add specific JDBC drivers to POM if needed);
    - Oracle;
    - PostgreSQL;
    - Microsoft SQL Server;
    - Informix;
    - H2;
    - Derby;
    - and many more...
  - Hibernate 5 as JPA 2.1 provider;
  - Full-featured CRUD operations over an entity;
    - CRUD filtering features using Spring Data QBE;
  - Internationalization;
  - Faces bean utilities using FacesUtils class static methods;
  - Twitter Bootstrap 3.3 templating;

- To run:
  - Checkout using Git or SVN;
  - Run using Maven 3 `mvn clean jetty:run`;

- Customize database (optional):
  - Create a database over your preferred SGBD;
  - Add the desired JAR to POM, if needed: built-in H2, PostgreSQL and MySQL drivers;
  - Configure database connection settings over `src/main/resources/datasource.properties`;

What's new
=============================

- Version 2.0.1.GA
  - Fixed datasource properties location.

- Version 2.0.0.GA
  - Pure JavaServer Faces 2.2 (no Primefaces required);
  - Spring Security 4.1 as Web Security Provider;
  - Hibernate 5 as JPA 2.1 provider;
  - Twitter Bootstrap 3.3 templating;

- Version 1.4.0.GA
  - Added H2 JAR dependency for built-in support to H2 database;
  - Set H2 as default database (no configurations needed to run the project);

- Version 1.3.1.GA
  - Fixed a bug in the edition form that, in some circumstances, was keeping outdated validation data: added attribute resetValues="true" to the insertion and edition action buttons;

- Version 1.3.0.GA
  - Added a full-featured CRUD operations example: possibility to READ over a table, CREATE, EDIT and DELETE entries;
  - Changed default entity from "Code" to "Country", with properties:
    - Name (text);
    - Acronym (text);
    - Population (numeric);
  - Added a Spring application context listener that automatically adds mock data to database;
  - Moved internal page CSS to an external file at the `resources` folder;
  - Updated dependencies versions;

- Version 1.2.2.GA
  - Fixed JPA Transaction Manager bean name to conventional "transactionManager";

- Version 1.2.1.GA
  - Added PostgreSQL 9.x JDBC 4.1 driver;
  - Project encoding strictly defined as UTF-8;
  - Updated Primefaces version to 5.1;

- Version 1.2.0.GA
  - Full support component injection (@Inject and @Autowired) by extending <del>the new utility class InjectionAwareBean</del> Spring context's `SpringBeanAutowiringSupport` utility class; 

- Version 1.1.2.GA
  - Changed `index.xhtml` to show a `p:datatable` with mock objects;
  - Changed default Primefaces Aristo theme to Bootstrap;

- Version 1.1.0.GA
  - Internationalization;
  - Bean utilities (class FacesUtils);
  - BUG: Spring does not fully support CDI, then @Named could not be used to define JSF Managed Beans;

- Version 1.0.4.GA
  - Built-in MySQL Server connection for JDBC/JPA (extensible to ANY database - add specific JDBC drivers to POM if needed);
    - Oracle;
    - PostgreSQL;
    - Microsoft SQL Server;
    - Informix;
    - H2;
    - Derby;
    - and many more...
  - Hibernate 4.x as JPA 2.1 provider (switchable to EclipseLink if convenient - see web.xml);
