FROM openjdk:21

EXPOSE 8081

COPY target/Hibernate-0.0.1-SNAPSHOT.jar cloudapp.jar

CMD ["java", "-jar", "cloudapp.jar"]