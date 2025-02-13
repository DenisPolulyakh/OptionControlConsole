# Стадия сборки
FROM eclipse-temurin:21-jammy AS builder

# Устанавливаем необходимые пакеты
RUN apt-get update && \
    apt-get install -y wget tar

# Скачиваем и устанавливаем Maven
ENV MAVEN_VERSION 3.9.0
ENV MAVEN_HOME /opt/apache-maven-${MAVEN_VERSION}
ENV PATH ${MAVEN_HOME}/bin:${PATH}

RUN wget -qO- https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | \
    tar zxf - -C /opt && \
    echo "Maven installed successfully!" || { echo "Failed to extract Maven archive"; exit 1; }

# Устанавливаем рабочий каталог
WORKDIR /app

# Копируем исходники в контейнер
COPY pom.xml .
COPY src ./src

# Собираем проект с использованием Maven
RUN mvn clean package -DskipTests

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