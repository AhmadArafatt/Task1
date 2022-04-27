#FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
#WORKDIR /opt
#ENV PORT 8081
#EXPOSE 8081
#COPY target/*.jar /opt/app.jar
#ENTRYPOINT exec java $JAVA_OPTS -jar app.jar

FROM openjdk:8
WORKDIR /tmp
ADD target/TestPro-0.0.2-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]