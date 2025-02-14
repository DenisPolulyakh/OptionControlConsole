FROM eclipse-temurin:21-jammy

WORKDIR /app

# Обновляем список пакетов
RUN apt-get update && apt-get install -y curl

COPY . /app/

RUN chmod +x /app/mvnw

EXPOSE 8585

ENTRYPOINT [ "/app/mvnw", "spring-boot:run" ]