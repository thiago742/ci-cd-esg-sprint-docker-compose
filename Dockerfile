FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY esg-monitor/pom.xml .
COPY esg-monitor/src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]