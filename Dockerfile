FROM        eclipse-temurin:21-jammy
WORKDIR /app
COPY . .
EXPOSE 8585
ENTRYPOINT ["mvn", "spring-boot:run"]