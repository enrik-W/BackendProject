FROM openjdk:18 AS build-stage
WORKDIR /build
COPY .mvn .mvn/
COPY pom.xml .
COPY src src/
COPY mvnw .

RUN chmod +x ./mvnw
# Tests can be skipped with -DskipTests
RUN ./mvnw package -DskipTests

FROM openjdk:18

WORKDIR /app
COPY --from=build-stage /build/target/BackendProjectOne-*.jar /app/api.jar
EXPOSE 8080
CMD ["java", "-jar", "api.jar", "-Djava.net.preferIPv4Stack=true"]
