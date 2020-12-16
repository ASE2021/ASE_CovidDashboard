FROM maven:3.6.3-jdk-11 AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

ARG SONAR_PROJECT_KEY=robinmanuelthiel_microcommunication
ARG SONAR_OGRANIZAION_KEY="ase-project-group"
ARG SONAR_HOST_URL=https://sonarcloud.io
ARG SONAR_TOKEN

RUN mvn package

FROM openjdk:11-jdk



COPY --from=MAVEN_TOOL_CHAIN /tmp/target/ase-0.0.1-SNAPSHOT.jar /spring-boot.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","spring-boot.jar"]
