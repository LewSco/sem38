FROM openjdk:latest
COPY ./target/sem38.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem38.jar", "database:3306", "30000"]