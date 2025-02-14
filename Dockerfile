FROM eclipse-temurin:21 as builder

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем проект в контейнер
COPY pom.xml /app/
COPY src /app/src/

# Собираем проект и создаем JAR-файл
RUN mvn clean package

# Финальный этап сборки
FROM eclipse-temurin:21

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем JAR-файл из этапа сборки
COPY --from=builder /app/target/*.jar /app/app.jar

# Открываем порт
EXPOSE 8585

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app/app.jar"]