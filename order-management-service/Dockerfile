FROM gradle:jdk17

EXPOSE 8088

## Use this if you want to just copy the executable
COPY order-management-service/build/libs/order-management-service.jar app.jar
COPY order-management-service/build/resources resources
CMD java -jar app.jar --debug

# Use this if you want to build in docker
#WORKDIR /app
#COPY . .
#WORKDIR customer-service
#RUN gradle bootJar --no-daemon
#
#CMD gradle bootRun --no-daemon
