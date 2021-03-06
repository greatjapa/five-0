FROM openjdk:8-jdk-alpine
WORKDIR /
COPY . /

EXPOSE 8080
ADD target/five-0-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]