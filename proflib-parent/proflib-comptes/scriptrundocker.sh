ansible-playbook -i ansible/env/local/00_inventory.yml -C ansible/playbook.yml
#docker run -d --name=proflibcomptesdb  -p3306:3306 -e "MYSQL_ROOT_PASSWORD=chenlaouche" -e "MYSQL_DATABASE proflibcomptesdb" mariadb:latest
$M3/mvn clean
$M3/mvn package
#docker build -t proflib-web:1 -f Dockerfile .
#java -jar target/proflib-comptes-microbundle.jar --nocluster --logtofile proflib-comptes.log --postbootcommandfile postbootPayara.conf
#/usr/lib/jvm/java-11-openjdk-amd64/bin/java -jar target/proflib-comptes-microbundle.jar --nocluster --logtofile proflib-comptes.log --postbootcommandfile postbootPayara.conf
#docker run proflib-web:1
docker-compose build
docker-compose up