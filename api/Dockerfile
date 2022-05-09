FROM openjdk:18 AS build-stage

WORKDIR /build

COPY ./mvnw .
COPY ./.mvn .mvn/
COPY ./pom.xml .
COPY ./src/ src/

RUN ./mvnw package -DskipTests
RUN ./mvnw package

FROM openjdk:18

WORKDIR /app
COPY --from=build-stage /build/target/webshop-api-*jar /app/webshop-api.jar
EXPOSE 8080
CMD ["java", "-jar", "webshop-api.jar", "-Djava.net.preferIPv4Stack=true"]