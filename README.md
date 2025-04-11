# 📚 Microservicio de Libros — Parcial 2

Este proyecto es un microservicio desarrollado con Spring Boot que gestiona un CRUD de libros, siguiendo buenas prácticas de arquitectura en capas, contenedorización con Docker, pruebas unitarias y de integración, y generación de reportes de cobertura con JaCoCo. Forma parte del segundo parcial de la asignatura.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- MariaDB
- H2 Database (para pruebas)
- Docker & Docker Compose
- JUnit 5 + Mockito
- JaCoCo (para cobertura)
- Thunder Client (para pruebas manuales)

---

## ⚙️ Estructura del proyecto

```
📆 src
 ├ 📁 main
 ┃ ├ 📁 java/com.juanma1301.libros
 ┃ ┃ ├ 📁 controller
 ┃ ┃ ├ 📁 service
 ┃ ┃ ├ 📁 repository
 ┃ ┃ └ 📁 model
 ┃ └ 📁 resources
 ┃   └ 📄 application.properties
 ├ 📁 test/java/com.juanma1301.libros
 ┃ ├ 📄 LibroControllerTest.java
 ├ 📄 Dockerfile
 ├ 📄 docker-compose.yml
 ├ 📄 run.ps1
 └ 📄 README.md
```

---

## 🐳 Cómo ejecutar el proyecto con Docker

1. Asegurate de tener Docker instalado y en ejecución
2. Ejecutá el script:

### 🔁 En Windows (PowerShell):

```powershell
.\run.ps1
```

> Esto construirá la imagen, levantará la base de datos y la aplicación, y dejará todo listo.

📌 Accede a la API en:  
`http://localhost:8080/api/libros`

---

## 📆 Endpoints principales

| Método | Endpoint               | Descripción               |
|--------|------------------------|---------------------------|
| POST   | /api/libros            | Crear un nuevo libro      |
| GET    | /api/libros            | Listar todos los libros   |
| GET    | /api/libros/{id}       | Obtener libro por ID      |
| PUT    | /api/libros/{id}       | Actualizar un libro       |
| DELETE | /api/libros/{id}       | Eliminar un libro         |

---

## 📂 Respaldo automático de la base de datos

El script crea una carpeta `/backup` dentro del contenedor `mariadb` y guarda automáticamente un respaldo con la fecha actual:

```
/backup/respaldo-librosdb-YYYY-MM-DD.sql
```

📌 El respaldo también puede copiarse con:

```bash
docker cp mariadb:/backup/respaldo-librosdb-2025-04-10.sql .
```

---

## 🥯 Pruebas y cobertura

### ▶️ Ejecutar los tests:

```bash
mvn test
```

### 📊 Generar reporte de cobertura:

```bash
mvn verify
```

### 📂 Ver el reporte:

Abrí en tu navegador:

```
target/site/jacoco/index.html
```

---

## 🐳 Imagen en Docker Hub

La imagen del microservicio está publicada en:

🔗 [https://hub.docker.com/repository/docker/juanma1301/libros-app](https://hub.docker.com/repository/docker/juanma1301/libros-app)

### Para descargarla directamente:

```bash
docker pull juanma1301/libros-app:latest
```

---

## ⚡ Colección de pruebas (Thunder Client)

El proyecto fue probado con la extensión **Thunder Client** en VS Code. Podés importar la colección desde el archivo `thunder-collection_parcial2-contenedores.json`.

---

## 📌 Autor

**Juan Manuel**  
Estudiante de Ingeniería de Software  
Parcial 2 — Contenedores y Microservicios  
🗕️ Abril 2025

---

## ✅ Estado del proyecto

| Funcionalidad          | Estado         |
|------------------------|----------------|
| CRUD libros            | ✅ Completo    |
| Docker Compose         | ✅ Configurado |
| Pruebas unitarias      | ✅ Funcionales |
| Backup de DB           | ✅ Con fecha   |
| Imagen Docker Hub      | ✅ Publicada   |
| Reporte cobertura      | ✅ Generado    |
| Script automático      | ✅ Incluido    |

