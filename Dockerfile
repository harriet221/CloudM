FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} cloudM.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "cloudM.jar"]
