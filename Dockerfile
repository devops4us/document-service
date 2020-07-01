FROM open-liberty:19.0.0.9-microProfile1-java8-openj9
COPY ./src/main/liberty/server.xml /config
COPY ./target/document-service-0.1-SNAPSHOT.war /config/apps
EXPOSE 9080