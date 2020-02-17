FROM tomcat:9.0.31-jdk8-openjdk-slim
COPY /target/ROOT.war /usr/local/tomcat/webapps/



