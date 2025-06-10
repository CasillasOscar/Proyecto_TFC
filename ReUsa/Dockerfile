# --- ETAPA DE CONSTRUCCIÓN (BUILD STAGE) ---
FROM maven:3.8.2-jdk-17 AS builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM maven:3.8.2-openjdk-17 AS builder

WORKDIR /app

COPY --from=builder /app/target/ReUsa-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]