FROM openjdk:latest
COPY ./target/sem38-0.1.0.5-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem38-0.1.0.5-jar-with-dependencies.jar"]