# Стадия сборки
FROM eclipse-temurin:21-jammy AS builder

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем исходники в контейнер
COPY pom.xml .
COPY src ./src

# Собираем проект с использованием Maven
RUN mvn clean package -DskipTests

### Run stage
FROM eclipse-temurin:21-jammy

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем собранный JAR-файл из стадии сборки
COPY --from=builder /app/target/*.jar app.jar

# Открываем порт
EXPOSE 8585

# Запускаем приложение
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]