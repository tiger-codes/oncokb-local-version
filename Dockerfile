#
# Build stage
#
FROM maven:3.3.9-jdk-8 AS build
COPY . .

COPY pom.xml /home/app
RUN mvn clean && mvn package -P curate -DskipTests


#
# Package stage
#
FROM openjdk:8-jre
COPY web /web/target
COPY web/target/dependency/webapp-runner.jar /webapp-runner.jar
COPY web/target/*.war /app.war
# specify default command
ENTRYPOINT java ${JAVA_OPTS} -jar /webapp-runner.jar ${WEBAPPRUNNER_OPTS} /app.war