#docker run -d --name=proflibcomptesdb  -p3307:3306 -e "MYSQL_ROOT_PASSWORD=chenlaouche" -e "MYSQL_DATABASE proflibcomptesdb" mariadb:latest

$M3/mvn clean
$M3/mvn package
#docker build -t proflib-web:1 -f Dockerfile .
#java -jar target/proflib-comptes-microbundle.jar --nocluster --logtofile proflib-comptes.log --postbootcommandfile postbootPayara.conf
java -jar target/proflib-comptes-microbundle.jar --nocluster --logtofile proflib-comptes.log
#docker run proflib-web:1
