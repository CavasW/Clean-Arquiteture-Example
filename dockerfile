FROM maven:sapmachine as maven

WORKDIR /usr/src/app
COPY . /usr/src/app/

RUN mvn clean package

# Java 17
FROM eclipse-temurin:17-jdk-alpine
ARG EXECUTABLE=backend-*.jar

WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${EXECUTABLE} /opt/app/

ENTRYPOINT [ "java", "-jar", "backend-3.0.2.jar" ]