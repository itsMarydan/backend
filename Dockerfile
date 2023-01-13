FROM openjdk:19
ADD build/libs/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","backend-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080