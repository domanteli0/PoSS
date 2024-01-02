FROM gradle:jdk17

EXPOSE 8080

## Use this if you want to just copy the executable
COPY customer-service/build/libs/customer-service.jar app.jar
CMD java -jar app.jar

# Use this if you want to build in docker
#WORKDIR /app
#COPY . .
#WORKDIR customer-service
#RUN gradle bootJar --no-daemon
#
#CMD gradle bootRun --no-daemon
