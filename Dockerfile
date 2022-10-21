FROM amazoncorretto:17
ADD target/veterinarian.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]