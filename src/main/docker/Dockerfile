FROM amazoncorretto:11-alpine-jdk
COPY target/cdc.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]