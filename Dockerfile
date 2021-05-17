FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim
COPY target/coding-dojo-spring-boot-0.0.1.jar coding-dojo-spring-boot-0.0.1.jar
ENTRYPOINT ["java","-jar","/coding-dojo-spring-boot-0.0.1.jar"]