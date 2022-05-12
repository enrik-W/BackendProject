FROM openjdk:18 AS build-stage
WORKDIR /build
COPY .mvn .mvn/
COPY pom.xml .
COPY src src/
COPY mvnw .

RUN chmod +x ./mvnw
RUN ./mvnw package

FROM openjdk:18

WORKDIR /app
COPY --from=build-stage /build/target/api-*.jar /app/api.jar
EXPOSE 8080
CMD ["java", "-jar", "api.jar", "-Djava.net.preferIPv4Stack=true"]