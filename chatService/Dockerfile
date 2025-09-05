# === Stage 1: Сборка с кэшированием ===
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Копируем конфигурацию Gradle
COPY gradle ./gradle
COPY settings.gradle* ./
COPY build.gradle* ./
COPY gradlew ./

RUN chmod +x gradlew

# Кэшируем зависимости
RUN --mount=type=cache,id=gradle-cache,target=/root/.gradle \
    ./gradlew dependencies --no-daemon --refresh-dependencies

# Копируем исходники
COPY src ./src

# Собираем JAR (без тестов)
RUN --mount=type=cache,id=gradle-cache,target=/root/.gradle \
    ./gradlew build -x test --no-daemon

# === Stage 2: Продакшен-образ (лёгкий) ===
FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем JAR
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# Запуск с ускорением инициализации
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]