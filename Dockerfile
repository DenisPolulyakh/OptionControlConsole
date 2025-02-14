FROM eclipse-temurin:21-jammy

# Копируем файлы проекта в контейнер
COPY . /app/

# Устанавливаем рабочую директорию
WORKDIR /app/

# Проверяем наличие файла mvnw
RUN ls -l .

# Устанавливаем права на исполнение для mvnw
RUN chmod a+x ./mvnw

# Открываем порт
EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]