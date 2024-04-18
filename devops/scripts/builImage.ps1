##VARIAVEIS
$DATABASE_LOGIN="root"
$DATABASE_PASSWORD="Tet3yAS23sa5."
$DATABASE_URL="jdbc:mysql://172.20.0.2:3306/presentdb"
$ROUTE_EUREKA="http://172.20.0.3:8090/eureka/"

##SALVAR PASTA ATUAL
$PASTA_ATUAL = Get-Location

##MAVEN BUILD
mvn clean install

$CONTAINERS=("present-database-mysql", "present-face-id", "present-auths", "present-attendances","present-attendaces", "present-api-gateway", "present-classes", "present-courses", "present-disciplines", "present-events", "present-groups", "present-service-registry", "present-users")

foreach ($CONTAINER in $CONTAINERS) {
    if (docker ps -a --format '{{.Names}}' | Select-String -Pattern "^$CONTAINER$" -Quiet) {
        Write-Host "Removendo contêiner $CONTAINER"
        docker rm -f $CONTAINER
    }
    else {
        Write-Host "O contêiner $CONTAINER não existe. Nenhuma ação necessária."
    }
}

##DOCKER BUILD
cd $PASTA_ATUAL/devops/present-database/
docker build -t present-database-mysql .

cd $PASTA_ATUAL/present-attendances
docker build -t present-attendances .

cd $PASTA_ATUAL/present-api-gateway
docker build -t present-api-gateway .

cd $PASTA_ATUAL/present-classes
docker build -t present-classes .

cd $PASTA_ATUAL/present-courses
docker build -t present-courses .

cd $PASTA_ATUAL/present-disciplines
docker build -t present-disciplines .

cd $PASTA_ATUAL/present-events
docker build -t present-events .

cd $PASTA_ATUAL/present-groups
docker build -t present-groups .

cd $PASTA_ATUAL/present-service-registry
docker build -t present-service-registry .

cd $PASTA_ATUAL/present-users
docker build -t present-users .

# cd $PASTA_ATUAL/present-auths
# docker build -t present-auths .

# cd $PASTA_ATUAL/present-face-id
# docker build -t present-face-id .

##CREATE DOCKER NETWORK
Write-Host "Criando rede..."
docker network create -d bridge present-container-network
Write-Host "Rede criada!"

##DOCKER RUN
docker run -d -p 3306:3306 --network present-container-network --name present-database-mysql -e MYSQL_ROOT_PASSWORD=$DATABASE_PASSWORD -e MYSQL_ROOT_HOST='%' present-database-mysql
sleep 20

docker run -d -p 8090:8090 --network present-container-network --name present-service-registry -e EUREKA_URL=$ROUTE_EUREKA -e MICROSERVICE_NAME=present-service-registry -e MICROSERVICE_PORT=8090 present-service-registry
sleep 20

docker run -d -p 8089:8089 --network present-container-network --name present-users -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-users -e MICROSERVICE_PORT=8089 -e MICROSERVICE_RELOADPORT=35739 -e ROUTE_EUREKA=$ROUTE_EUREKA present-users
 
docker run -d -p 8088:8088 --network present-container-network --name present-groups -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-groups -e MICROSERVICE_PORT=8089 -e MICROSERVICE_RELOADPORT=35739 -e ROUTE_EUREKA=$ROUTE_EUREKA present-groups

docker run -d -p 8086:8086 --network present-container-network --name present-events -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-events -e MICROSERVICE_PORT=8086 -e MICROSERVICE_RELOADPORT=35736 -e ROUTE_EUREKA=$ROUTE_EUREKA present-events

docker run -d -p 8085:8085 --network present-container-network --name present-disciplines -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-disciplines -e MICROSERVICE_PORT=8085 -e MICROSERVICE_RELOADPORT=35735 -e ROUTE_EUREKA=$ROUTE_EUREKA present-disciplines

docker run -d -p 8084:8084 --network present-container-network --name present-courses -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-courses -e MICROSERVICE_PORT=8084 -e MICROSERVICE_RELOADPORT=35734 -e ROUTE_EUREKA=$ROUTE_EUREKA present-courses

docker run -d -p 8083:8083 --network present-container-network --name present-classes -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-classes -e MICROSERVICE_PORT=8083 -e MICROSERVICE_RELOADPORT=35733 -e ROUTE_EUREKA=$ROUTE_EUREKA present-classes

docker run -d -p 8081:8081 --network present-container-network --name present-attendances -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-attendances -e MICROSERVICE_PORT=8081 -e MICROSERVICE_RELOADPORT=35731 -e ROUTE_EUREKA=$ROUTE_EUREKA present-attendances

docker run -d -p 8080:8080 --network present-container-network --name present-api-gateway -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-api-gateway -e MICROSERVICE_PORT=8080 -e MICROSERVICE_RELOADPORT=35730 -e ROUTE_EUREKA=$ROUTE_EUREKA present-api-gateway

# docker run -d -p 8082:8082 --network present-container-network --name present-auths -e DATABASE_LOGIN=$DATABASE_LOGIN -e DATABASE_PASSWORD=$DATABASE_PASSWORD -e DATABASE_URL=$DATABASE_URL -e MICROSERVICE_NAME=present-auths -e MICROSERVICE_PORT=8082 -e MICROSERVICE_RELOADPORT=35732 -e ROUTE_EUREKA=$ROUTE_EUREKA present-auths

# docker run -d -p 808x:808x --network present-container-network --name present-face-id present-face-id

cd $PASTA_ATUAL

