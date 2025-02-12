FROM        eclipse-temurin:21-jammy
WORKDIR /app
COPY . .

# Добавляем текущий каталог в PATH
ENV PATH="${PATH}:/app"

EXPOSE 8585
ENTRYPOINT [ "mvnw", "spring-boot:run"]