FROM openjdk:11
ADD target/fund-manager-0.0.1-SNAPSHOT.war fund-manager.war
EXPOSE 8090
ENTRYPOINT ["java","-jar","/fund-manager.war"]
