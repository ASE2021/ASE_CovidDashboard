FROM maven:3.6.3-jdk-11 AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:11-jdk

COPY --from=MAVEN_TOOL_CHAIN /tmp/target/ase-0.0.1-SNAPSHOT.jar /spring-boot.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","spring-boot.jar"]
