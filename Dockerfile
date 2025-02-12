FROM        eclipse-temurin:21-jammy
WORKDIR /app
COPY . .

# Установите переменную PATH
ENV PATH="/app:${PATH}"

EXPOSE 8585
ENTRYPOINT [ "mvnw", "spring-boot:run"]