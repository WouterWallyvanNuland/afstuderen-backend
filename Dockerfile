FROM openjdk:8-alpine

WORKDIR /app
COPY target/backend-1.0-SNAPSHOT-jar-with-dependencies.jar backend-application.jar

EXPOSE 4567
ENTRYPOINT ["java", "-jar", "/app/backend-application.jar"]