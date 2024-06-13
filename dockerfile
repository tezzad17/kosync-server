FROM amazoncorretto:21-alpine-jdk

COPY target/kosync-server-0.0.1-SNAPSHOT.jar kosync-server-0.0.1-SNAPSHOT.jar

RUN mkdir -p /config

ENTRYPOINT ["java","-jar","/kosync-server-0.0.1-SNAPSHOT.jar"]