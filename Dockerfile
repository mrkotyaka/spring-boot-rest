FROM openjdk:21-slim

EXPOSE 8080

ADD build/libs/spring-boot-rest-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]