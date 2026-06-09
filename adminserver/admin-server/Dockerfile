FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/admin-server.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]