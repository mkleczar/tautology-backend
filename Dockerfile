FROM openjdk:15-alpine3.12

COPY target/tautology-backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

