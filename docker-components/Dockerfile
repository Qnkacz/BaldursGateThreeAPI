FROM eclipse-temurin:17-alpine
LABEL authors="bartek"

RUN mkdir /opt/app
COPY *.jar /opt/app/app.jar

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]