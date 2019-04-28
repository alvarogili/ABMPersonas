# we are extending everything from tomcat:8.0 image ...
FROM tomcat:jre8

MAINTAINER alvarogili@gmail.com

COPY Personas-back/target/personas.war /usr/local/tomcat/webapps/

RUN mkdir /opt/ABMPersonas/

COPY Personas-back/src/main/resources/config.properties /opt/ABMPersonas/