FROM tomcat
MAINTAINER 2828878@gmail.com
RUN rm -r /usr/local/tomcat/webapps && mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps
COPY target/*.war /usr/local/tomcat/webapps
