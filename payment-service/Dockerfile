FROM gradle:jdk17

EXPOSE 8090

## Use this if you want to just copy the executable
COPY payment-service/build/libs/payment-service.jar app.jar
COPY payment-service/build/resources resources
CMD java -jar app.jar --debug

# Use this if you want to build in docker
#WORKDIR /app
#COPY . .
#WORKDIR customer-service
#RUN gradle bootJar --no-daemon
#
#CMD gradle bootRun --no-daemon
