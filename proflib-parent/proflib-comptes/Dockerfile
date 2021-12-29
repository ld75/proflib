FROM payara/micro:5.2021.9-jdk11
USER root
COPY target/proflib-comptes.war /opt/payara/deployments/proflib-comptes.war
COPY postbootPayara.conf /opt/payara/deployments/

ENTRYPOINT ["java", "-Dlog.level=DEBUG","-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=90.0","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar", "payara-micro.jar"]
CMD ["--deploymentDir","/opt/payara/deployments","--contextroot","/","--rootDir","tmppayara","--port","8080","--postbootcommandfile","/opt/payara/deployments/postbootPayara.conf"]
