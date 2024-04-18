FROM openjdk:21
WORKDIR /app
COPY target/present-service-registry-1.0.0.jar /app/present-service-registry-1.0.0.jar
CMD ["java", "-jar", "present-service-registry-1.0.0.jar"]
EXPOSE 8080

# mvn clean package -DEUREKA_URL=http://localhost:8090/eureka/ -DMICROSERVICE_NAME=present-service-registry -DMICROSERVICE_PORT=8090

# java -jar .\target\present-service-registry-1.0.0.jar                                                                                                                  

# mvn spring-boot:run -DEUREKA_URL=http://localhost:8090/eureka/ -DMICROSERVICE_NAME=present-service-registry -DMICROSERVICE_PORT=8090

# docker build -t present-service-registry:0.0.1 .

# docker run -d -p 8090:8090 --network present-container-network --name present-service-registry -e ROUTE_EUREKA=http://localhost:8090/eureka/ -e MICROSERVICE_NAME=present-service-registry -e MICROSERVICE_PORT=8090 present-service-registry:001