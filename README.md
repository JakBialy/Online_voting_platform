# Online_voting_platform
Project aiming at creation of online voting platform
which could be possibly used by companies, association etc.

Application is using Spring Boot with several dependencies: Jpa, Security, Web, Thymeleaf, Lombok with dependency managment by Gradle. For data storage stands PostgreSQL relation database.

Application in the end of development phase should be able to store user data (security, basic identification) and companies 
data (votes, votes result). It will be able to represent results in form or graphs, pdfs or reports which could be send into e-mail
after vote is finished (and store historic data).

For now it is just a very basic application which allows to vote on very limited test data and without ability to set new ones.

To do list:
- finish tests for all services and repositories
- add DTO and conversions DTO/Models
- add Admin panel, and ablity for allowing him for setting/modification
- dockerization
- mail service for registration/information of started/finished votes or reports
- pdf binder (reports) or .csv file
- api for allowing clients to have possiblity to import information to some external system,
protected by tokens
