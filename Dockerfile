# Стадия запуска
FROM eclipse-temurin:21-jammy

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем собранный JAR-файл из стадии сборки
COPY --from=builder /app/target/*.jar app.jar

# Открываем порт
EXPOSE 8585

# Запускаем приложение
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]