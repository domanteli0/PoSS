FROM gradle:jdk17

EXPOSE 8085

## Use this if you want to just copy the executable
COPY staff-service/build/libs/staff-service.jar app.jar
COPY staff-service/build/resources resources
CMD java -jar app.jar --debug

# Use this if you want to build in docker
#WORKDIR /app
#COPY . .
#WORKDIR staff-service
#RUN gradle bootJar --no-daemon
#
#CMD gradle bootRun --no-daemon
