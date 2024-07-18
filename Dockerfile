FROM openjdk:8
EXPOSE 9000
ADD target/mmtspl-employee-service-1.0.0-SNAPSHOT.jar mmtspl-employee-service-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/mmtspl-employee-service-1.0.0-SNAPSHOT.jar"]