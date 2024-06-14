FROM maven:3.9.7-amazoncorretto-21 AS build

RUN mkdir -p workspace

WORKDIR /workspace

COPY pom.xml /workspace

COPY src /workspace/src

RUN ls /workspace

COPY src/main/resources/persistence-prod.properties /workspace/src/main/resources/persistence.properties 

RUN mvn -B package --file pom.xml -DskipTests

FROM amazoncorretto:21-alpine-jdk

COPY --from=build /workspace/target/*.jar kosync-server.jar

RUN mkdir -p /config

EXPOSE 8080

ENTRYPOINT ["java","-jar","/kosync-server.jar"]