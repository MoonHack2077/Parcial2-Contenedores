# ---------- Etapa 1: Compilación ----------
    FROM maven:3.9.6-eclipse-temurin-17 AS build
    WORKDIR /app
    
    # Copiar archivos de configuración y dependencias
    COPY pom.xml .
    COPY src ./src
    
    # Descargar dependencias y compilar el proyecto
    RUN mvn clean package -DskipTests
    
    # ---------- Etapa 2: Imagen de producción ----------
    FROM eclipse-temurin:17-jdk-alpine
    WORKDIR /app
    
    # Copiar el jar desde la etapa anterior
    COPY --from=build /app/target/libros-0.0.1-SNAPSHOT.jar app.jar
    
    # Exponer puerto
    EXPOSE 8080
    
    # Ejecutar la aplicación
    ENTRYPOINT ["java", "-jar", "app.jar"]
    