##Indica cual jdk usar
FROM amazoncorretto:17
##Define el directorio de trabajo
WORKDIR /app
##Copia el jar al directorio de trabajo
COPY target/event-handler-1.0-SNAPSHOT.jar /app/event-handler-1.0-SNAPSHOT.jar
##Expone el puerto
EXPOSE 8080
##Ejecuta la aplicacion
ENTRYPOINT ["java", "-jar", "event-handler-1.0-SNAPSHOT.jar"]
