# --- ETAPA DE CONSTRUCCIÓN (BUILD STAGE) ---
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/ReUsa/target/ReUsa-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]