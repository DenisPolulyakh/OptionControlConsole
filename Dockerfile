FROM maven:3.9.9-eclipse-temurin-21
WORKDIR /app
COPY . .
EXPOSE 8585
ENTRYPOINT ["mvn", "spring-boot:run"]