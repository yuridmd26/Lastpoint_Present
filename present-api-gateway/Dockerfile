FROM openjdk:21
WORKDIR /app
COPY target/present-api-gateway-1.0.0.jar /app/present-api-gateway-1.0.0.jar
CMD ["java", "-jar", "present-api-gateway-1.0.0.jar"]
EXPOSE 8080

# mvn clean package -DDATABASE_LOGIN="root" -DDATABASE_PASSWORD="Tet3yAS23sa5." -DDATABASE_URL="jdbc:mysql://localhost:3306/presentdb" -DMICROSERVICE_NAME="present-api-gateway" -DMICROSERVICE_PORT="8080" -DMICROSERVICE_RELOADPORT="35730" -DROUTE_EUREKA="http://localhost:8090/eureka/"

# docker build -t present-api-gateway .

# docker run -d -p 8080:8080 --network present-container-network --name present-api-gateway -e DATABASE_LOGIN=root -e DATABASE_PASSWORD=Tet3yAS23sa5. -e DATABASE_URL=jdbc:mysql://172.20.0.2:3306/presentdb -e MICROSERVICE_NAME=present-api-gateway -e MICROSERVICE_PORT=8080 -e MICROSERVICE_RELOADPORT=35730 -e ROUTE_EUREKA=http://172.20.0.3:8090/eureka/ present-api-gateway
