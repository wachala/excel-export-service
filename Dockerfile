FROM maven:3-jdk-8-slim
CMD java -jar /webapp/excel-export-service/target/excel-export-service-1.0-SNAPSHOT.jar
COPY ./ /webapp/excel-export-service