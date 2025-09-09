#FROM eclipse-temurin:21-jdk AS builder
#
#WORKDIR /app
#
#COPY gradle ./gradle
#COPY settings.gradle* ./
#COPY build.gradle* ./
#COPY gradlew ./
#
#RUN chmod +x gradlew
#
#RUN --mount=type=cache,id=gradle-cache,target=/root/.gradle \
#    ./gradlew dependencies --no-daemon --refresh-dependencies
#
#COPY src ./src
#
#RUN --mount=type=cache,id=gradle-cache,target=/root/.gradle \
#    ./gradlew build -x test --no-daemon
#
#FROM eclipse-temurin:21-jre
#
#WORKDIR /app
#
#COPY --from=builder /app/build/libs/*.jar app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

FROM eclipse-temurin:21-jdk-alpine as builder

RUN apk add --no-cache bash

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew

RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /app/build/libs/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]