# Стадия запуска
FROM eclipse-temurin:21-jammy

# Устанавливаем рабочий каталог
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Открываем порт
EXPOSE 8585

# Запускаем приложение
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]