#!/bin/bash -e
docker stop proflibcomptes
docker rm -f proflibcomptes
cd service-a
$M3/mvn clean package

java -jar target/proflib-microbundle.jar --postbootcommandfile postbootPayara.conf --port 8081

#docker build -t comptes ./Comptes/
#docker run -d --name=proflibcomptes  -p8083:8080 -e "DB_NAME=localhost" -e "DB_PORT=3306" --env JAVA_OPTS="-DDB_NAME=localhost -DB_PORT=3306" comptes:latest
#docker run -d --name=proflibcomptesdb  -p3306:3306 -e "MYSQL_ALLOW_EMPTY_PASSWORD=true" mariadb:latest
#curl -s -X GET http://localhost:8082/health
#curl -s -X GET http://localhost:8083/health
#curl -s -X GET http://localhost:8083/greet
