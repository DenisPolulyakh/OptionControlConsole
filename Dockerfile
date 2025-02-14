FROM eclipse-temurin:21-jammy

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем проект в контейнер
COPY . /app/

# Добавляем текущий каталог в PATH
ENV PATH="${PATH}:/app"
# Устанавливаем права на исполнение для mvnw
RUN chmod +x /app/mvnw

# Открываем порт
EXPOSE 8585

# Запускаем приложение
ENTRYPOINT [ "/app/mvnw", "spring-boot:run" ]