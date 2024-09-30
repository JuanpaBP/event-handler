##Esto le dice usamos jdk 17
FROM amazoncorretto:17
##aca van los archivos generados por la aplicación DENTRO del container
WORKDIR /app
##Aca agarramos el jar que esta en target y lo copiamos a app/ CON EL MISMO NOMBRE
COPY target/event-handler-1.0-SNAPSHOT.jar /app/event-handler-1.0-SNAPSHOT.jar
##Escuchar este puerto:
EXPOSE 8080
##Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "event-handler-1.0-SNAPSHOT.jar"]
