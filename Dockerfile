FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml . 
COPY src ./src

RUN mvn clean package -DskipTests=true -Dspring.profiles.active=test
RUN ls -la /app/target

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/credentials-registration-0.0.1-SNAPSHOT.jar credentials-registration-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "credentials-registration-service.jar"]
