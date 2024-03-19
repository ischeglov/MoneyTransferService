FROM --platform=linux/arm64 amazoncorretto:17-alpine-jdk
EXPOSE 5500
ADD target/MoneyTransferService-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]