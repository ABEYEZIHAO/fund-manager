FROM openjdk:11
ADD fundmanager.war fundmanager.war
EXPOSE 8090
ENTRYPOINT ["java","-jar","/fundmanager.war"]
