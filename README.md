Resful JSON Web Service
========================
maven rest mysql spatial polygon json bootstrap

##Spatial Service
* http://localhost:8080/rest/spatial/list?lat=34.0561&lon=-118.4297&radius=100 

##Test Web Service
* http://localhost:8080/rest/search/list?id=15 
* http://localhost:8080/rest/search/info?id=15


##Deployment
* mvn clean install
* mvn tomcat:deploy
* mvn tomcat:undeploy

##References:
* https://github.com/spring-projects
* http://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-first-application.html
* http://projects.spring.io/spring-boot/
* http://patrickgrimard.com/2014/08/14/how-to-build-a-spring-boot-application-using-intellij-idea/

##Maven Deployment Tomcat Users: mvn tomcat:deploy 

```
vi ~/tomcat/conf/tomcat-users.xml

<role rolename="manager-gui"/>
<user password="manager" roles="manager-gui" username="manager"/>
<user password="admin" roles="manager-gui,manager-script,admin" username="admin"/>
```
