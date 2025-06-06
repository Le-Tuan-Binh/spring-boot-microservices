#Start with a base image containing Java runtime
FROM openjdk:24-jdk-slim

#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="tbin.com"

# Add the application's jar to the image
COPY target/account-service-1.0.0.jar account-service-1.0.0.jar

# Execute the application
ENTRYPOINT ["java","-jar","account-service-1.0.0.jar"]