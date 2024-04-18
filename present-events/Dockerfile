FROM openjdk:21
WORKDIR /app
COPY target/present-events-1.0.0.jar /app/present-events-1.0.0.jar
CMD ["java", "-jar", "present-events-1.0.0.jar"]
EXPOSE 8089

# mvn clean package -DDATABASE_LOGIN="root" -DDATABASE_PASSWORD="Tet3yAS23sa5." -DDATABASE_URL="jdbc:mysql://localhost:3306/presentdb" -DMICROSERVICE_NAME="present-events" -DMICROSERVICE_PORT="8086" -DMICROSERVICE_RELOADPORT="35736" -DROUTE_EUREKA="http://localhost:8090/eureka/"

# docker build -t present-service-events .

# docker run -d -p 8086:8086 --network present-container-network --name present-service-events -e DATABASE_LOGIN=root -e DATABASE_PASSWORD=Tet3yAS23sa5. -e DATABASE_URL=jdbc:mysql://172.20.0.2:3306/presentdb -e MICROSERVICE_NAME=present-events -e MICROSERVICE_PORT=8086 -e MICROSERVICE_RELOADPORT=35736 -e ROUTE_EUREKA=http://172.20.0.3:8090/eureka/ present-service-events
