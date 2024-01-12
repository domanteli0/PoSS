FROM gradle:jdk17

EXPOSE 8081

## Use this if you want to just copy the executable
COPY customer-service/build/libs/customer-service.jar app.jar
COPY customer-service/build/resources resources
CMD java -jar app.jar --debug

# Use this if you want to build in docker
#WORKDIR /app
#COPY . .
#WORKDIR customer-service
#RUN gradle bootJar --no-daemon
#
#CMD gradle bootRun --no-daemon
