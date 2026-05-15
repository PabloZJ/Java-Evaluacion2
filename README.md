# TechStore API — Microservicio de Gestión de Productos

Microservicio RESTful desarrollado con Spring Boot para administrar el catálogo de productos de TechStore Chile. Incluye autenticación JWT y persistencia en PostgreSQL.

## Tecnologías

- Java 17
- Spring Boot 3.2
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL 15
- Maven
- Docker

## Requisitos previos

- Java 17 instalado
- Maven instalado
- Docker Desktop instalado

## Clonar el repositorio

git clone https://github.com/PabloZJ/Java-Evaluacion2.git
cd Java-Evaluacion2

## Levantar la base de datos

docker run --name techstore_db -e POSTGRES_DB=techstore -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin123 -p 5432:5432 -d postgres:15

## Compilar y ejecutar

mvn clean package -DskipTests
java -jar target/evaluacion2-0.0.1-SNAPSHOT.jar

La aplicación quedará disponible en http://localhost:8080

## Autenticación

Primero obtén el token JWT:

POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "admin@techstore.cl",
  "password": "Admin1234"
}

Usa el token retornado en todas las peticiones siguientes:
Authorization: Bearer <token>

## Endpoints

| Método | Endpoint | Descripción | HTTP |
|--------|----------|-------------|------|
| GET | /api/productos | Listar todos | 200 |
| GET | /api/productos/{id} | Buscar por ID | 200 |
| POST | /api/productos | Crear producto | 201 |
| PUT | /api/productos/{id} | Modificar producto | 200 |
| DELETE | /api/productos/{id} | Borrado lógico | 204 |

## Ejemplo de producto

{
  "nombre": "Laptop Lenovo IdeaPad",
  "descripcion": "Notebook 15.6 pulgadas, 8GB RAM, 512GB SSD",
  "precio": 499990,
  "stock": 15,
  "categoria": "Computación",
  "activo": true
}

## Estructura del proyecto

src/main/java/cl/techstore/api/
├── controller/
├── service/
├── repository/
├── model/
├── security/
└── dto/

## Ramas Git

- main — versión estable
- develop — desarrollo activo