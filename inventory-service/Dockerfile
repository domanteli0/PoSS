FROM gradle:jdk17

EXPOSE 8087

## Use this if you want to just copy the executable
COPY inventory-service/build/libs/inventory-service.jar app.jar
COPY inventory-service/build/resources resources
CMD java -jar app.jar --debug

# Use this if you want to build in docker
#WORKDIR /app
#COPY . .
#WORKDIR customer-service
#RUN gradle bootJar --no-daemon
#
#CMD gradle bootRun --no-daemon
