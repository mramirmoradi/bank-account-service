FROM maven:3.8.5-openjdk-17 as dependency_maven
MAINTAINER amirmoradi
COPY pom.xml ./
COPY .mvn ./.mvn
RUN mvn -X -B dependency:resolve

## BUILD
FROM dependency_maven as build_maven
COPY src ./src
RUN mvn clean install

## Run Jar File
FROM openjdk:17.0.2-jdk-oracle

COPY --from=build_maven /target/api-gateway-0.0.1-SNAPSHOT.jar run.jar
ENTRYPOINT ["java","-jar","./run.jar"]