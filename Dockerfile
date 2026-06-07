FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} student.jar
ENTRYPOINT ["java", "-jar","/student.jar"]