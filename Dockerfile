FROM tomcat:9.0.31-jdk8-openjdk-slim
COPY /target/docker-taxreports.war /usr/local/tomcat/webapps/app.war



