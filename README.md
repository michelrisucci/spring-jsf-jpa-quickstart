Spring + JSF + JPA Quickstart
=============================

- Version 1.2.1.GA

- What it is: a quickstart project containing initial structure to:
  - Spring Context 4.x as JEE context provider;
  - Jetty 9.2.x as Webserver;
  - Mojarra JavaServer Faces 2.2.x as JSF implementation;
  - Primefaces 5.x as JSF components library;
  - Built-in PostgreSQL and MySQL Server connection for JDBC/JPA (extensible to ANY database - add specific JDBC drivers to POM if needed);
    - Oracle;
    - PostgreSQL;
    - Microsoft SQL Server;
    - Informix;
    - H2;
    - Derby;
    - and many more...
  - Hibernate 4.x as JPA 2.1 provider (switchable to EclipseLink if convenient - see web.xml);
  - Internationalization;
  - Bean utilities;
  - Primefaces Twitter Bootstrap Theme.


- To run:
  - Checkout using Git or SVN;
  - Create a database over your preferred SGBD;
  - Configure database properties over `src/main/webapp/WEB-INF/database`;
  - Run using Maven 3.x `mvn clean jetty:run`;

What's new
=============================

- Version 1.2.1.GA
  - Added PostgreSQL 9.x JDBC 4.1 driver;
  - Project encoding strictly defined as UTF-8;
  - Updated Primefaces version to 5.1.

- Version 1.2.0.GA
  - Full support component injection (@Inject and @Autowired) by extending the new utility class InjectionAwareBean.

- Version 1.1.2.GA
  - Changed `index.xhtml` to show a `p:datatable` with mock objects;
  - Changed default Primefaces Aristo theme to Bootstrap.

- Version 1.1.0.GA
  - Internationalization;
  - Bean utilities (class FacesUtils);
  - BUG: Spring does not fully support CDI, then @Named could not be used to define JSF Managed Beans.

- Version 1.0.4.GA
  - Built-in MySQL Server connection for JDBC/JPA (extensible to ANY database - add specific JDBC drivers to POM if needed);
    - Oracle;
    - PostgreSQL;
    - Microsoft SQL Server;
    - Informix;
    - H2;
    - Derby;
    - and many more...
  - Hibernate 4.x as JPA 2.1 provider (switchable to EclipseLink if convenient - see web.xml).