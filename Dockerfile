FROM openjdk:11
ADD ./build/libs/LeaseApplication-1.0.jar LeaseApplication-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "LeaseApplication-1.0.jar"]
