# --- ETAPA DE CONSTRUCCIÓN (BUILD STAGE) ---
FROM maven:3.8.2-openjdk-17 AS builder

WORKDIR /app

COPY ReUsa/ ./ReUsa/

WORKDIR /app/ReUsa

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/ReUsa/target/ReUsa-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]