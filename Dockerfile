### Build stage
FROM eclipse-temurin:21-jammy AS builder

# Set the working directory inside the container
WORKDIR /tmp

# Copy the source code into the container
COPY target/*.jar app.jar

# Extract the layers
RUN java -Djarmode=layertools -jar app.jar extract

### Run stage
FROM eclipse-temurin:21-jammy

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем проект в контейнер
COPY . /app/

# Устанавливаем права на исполнение для mvnw
RUN chmod +x /app/mvnw

# Открываем порт
EXPOSE 8585

# Запускаем приложение
ENTRYPOINT [ "/app/mvnw", "spring-boot:run" ]