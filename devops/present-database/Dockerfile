FROM mysql/mysql-server
ENV MYSQL_ROOT_PASSWORD="Tet3yAS23sa5"
ENV MYSQL_ROOT_HOST="%"
COPY src/scripts/initial-script.sql /docker-entrypoint-initdb.d/
EXPOSE 3306
CMD ["mysqld"]

# docker network create -d bridge present-container-network

# docker build -t present-database-mysql .

# docker run -d -p 3306:3306 --network present-container-network --name present-database-mysql -e MYSQL_ROOT_PASSWORD=Tet3yAS23sa5. -e MYSQL_ROOT_HOST='%' present-database-mysql

# docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' 0dec7dd8f7e8