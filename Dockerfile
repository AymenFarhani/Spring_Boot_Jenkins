FROM eclipse-temurin:21-jdk-jammy
EXPOSE 8081
ADD target/Spring_Boot_Jenkins-0.0.1-SNAPSHOT.jar Spring_Boot_Jenkins.jar
ENTRYPOINT ["java", "-jar", "/Spring_Boot_Jenkins.jar"]