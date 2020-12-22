# shortster

Technologies UsedJava, Maven, Spring, Spring Boot, Mysql.
Prerequisite:
  1.JDK 1.8
  2.Maven
  3.Docker
  4.Docker-compose
System Documentation:
  After running app, check System documentation by hitting following 
  url.http://localhost:8080/swagger-ui/#/
To Run Project
  To run project, from project root directory run following commands.
  #This is for linux system, if you are running this on window. Change volume paths. 
  #Following will create Mysql containerdocker-compose up
  #This will compile and run application mvn clean compile install 
Mysql Connection
  If you are using docker compose to run Mysql container, no configuration is required, as everything is already configured. But if you want to connect to your local mysql db, change following properties in application.properties under resources folder.
  
  spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db
  spring.datasource.username=admin
  spring.datasource.password=abc123
