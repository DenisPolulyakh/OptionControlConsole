FROM        eclipse-temurin:21-jammy
WORKDIR /app
# Копируем проект в контейнер
COPY . /app/

# Добавляем текущий каталог в PATH
ENV PATH="${PATH}:/app"

EXPOSE 8585
ENTRYPOINT [ "mvnw", "spring-boot:run"]