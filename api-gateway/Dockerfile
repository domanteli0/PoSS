FROM gradle:jdk17

EXPOSE 8080

## Use this if you want to just copy the executable
COPY api-gateway/build/libs/api-gateway.jar app.jar
COPY api-gateway/build/resources resources
CMD java -jar app.jar

## Use this if you want to build in docker
#WORKDIR /app
#COPY . .
#WORKDIR api-gateway
#RUN gradle bootJar --no-daemon
#
#CMD java -jar build/libs/api-gateway.jar
