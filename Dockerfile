FROM openjdk:8u342-jdk
COPY "./build/libs/prueba-tecnica-0.0.1-SNAPSHOT.jar" "prueba-tecnica-1.0.jar"
ENTRYPOINT ["java", "-jar", "/prueba-tecnica-1.0.jar"]