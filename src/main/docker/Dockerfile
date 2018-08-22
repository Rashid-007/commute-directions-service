# To execute the shipped application, a java runtime is required
# Use an openjdk runtime by refering to image tag of openjdk on docker hub
FROM openjdk:8-jdk-alpine

RUN  apk update && apk upgrade && apk add netcat-openbsd

RUN mkdir -p /usr/local/commute-directions-service
ADD *.jar /usr/local/commute-directions-service/app.jar

# Expose port on container for inter-container communication
EXPOSE 8080
EXPOSE 8000

# Start application with a source of entropy
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/local/commute-directions-service/app.jar", "--server.port=8080"]

#ADD run.sh run.sh
#RUN chmod +x run.sh
#CMD ./run.sh

