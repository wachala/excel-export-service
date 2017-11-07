FROM java:openjdk-8-jre
CMD java -jar excel-export-service-1.0-SNAPSHOT.jar
ADD target/excel-export-service-1.0-SNAPSHOT.jar .