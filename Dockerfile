FROM eclipse-temurin:21-jammy

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем проект в контейнер
COPY target/*.jar /app/app.jar

# Открываем порт
EXPOSE 8585

# Запускаем приложение
ENTRYPOINT ["java","-Dserver.port=8585", "-jar", "/app/app.jar"]