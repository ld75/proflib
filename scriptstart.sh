#!/bin/bash -e
#docker stop proflibcomptes
#docker rm -f proflibcomptes
#docker stop proflibcomptesdb
#docker rm -f proflibcomptesdb
cd proflib-comptes
$M3/mvn clean package


#docker build -t comptes ./Comptes/
#docker run -d --name=proflibcomptes  -p8083:8080 -e "DB_NAME=localhost" -e "DB_PORT=3306" --env JAVA_OPTS="-DDB_NAME=localhost -DB_PORT=3306" comptes:latest
#>>>docker run -d --name=proflibcomptesdb  -p3307:3306 -e "MYSQL_ROOT_PASSWORD=chenlaouche" -e "MYSQL_DATABASE proflibcomptesdb"  -v /home/laurent/Documents/proflibdata:/var/lib/mysql  mariadb:latest
#curl -s -X GET http://localhost:8081/health
#curl -s -X GET http://localhost:8081/health
#OK: curl -s -X GET http://localhost:8081/data/hello
# OK: curl -s -X GET http://localhost:8081/data/config/injected
#OK: curl -s -X POST http://localhost:8081/data/rien/creationcompte --header "Content-Type: application/json" -d '{"nom":"toto","prenom":"titi","email":"jan@jean.com","mdp":"a","comfirmmdp":"a","typeCompte":"elevea"}'

java -jar target/proflib-microbundle.jar --postbootcommandfile postbootPayara.conf --port 8081
