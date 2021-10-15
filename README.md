# Applicación Control de Inventarios

### Pre-requisitos

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.5/gradle-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Flyway](https://flywaydb.org/)
* [PostgreSQL](https://www.postgresql.org/)
* [Lombok](https://projectlombok.org/)

### Dependencias

Para levantar la aplicación se debe contar con las siguientes versiones:

* JDK o OpenJDK v11+
* PostgreSQL v12+
* Gradle 7.2

### Instrucciones iniciales

* Tener instalado PostgreSQL en el puerto [5432].
* Se debe tener creada una base de datos con el nombre de [stock].

### Endpoints

* Listar todas las categorias: [GET](http://localhost:8080/categoria/all) (http://localhost:8080/categoria/all)
* Buscar categoria por
  id: [GET](http://localhost:8080/categoria?id=codigo_categoria) (http://localhost:8080/categoria?id=codigo_categoria)
* Registrar una categoria: [POST](http://localhost:8080/categoria) (http://localhost:8080/categoria)
* Actualizar una categoria: [PUT](http://localhost:8080/categoria/all) (http://localhost:8080/categoria/all)
* Listar todos los bienes: [GET](http://localhost:8080/bien/all) (http://localhost:8080/bien/all)
* Buscar un bien por id: [GET](http://localhost:8080/bien?id=codigo_bien) (http://localhost:8080/bien?id=codigo_bien)
* Buscar un bien por id con detalle
  extendido: [GET](http://localhost:8080/bien/detail?id=id_bien) (http://localhost:8080/bien/detail?id=id_bien)
* Registrar un bien: [POST](http://localhost:8080/categoria/all) (http://localhost:8080/categoria/all)
* Bienes disponibles por
  categoria: [GET](http://localhost:8080/bien/disponibles-categoria) (http://localhost:8080/bien/disponibles-categoria)
* Listar todos los bienes con su
  categoria: [GET](http://localhost:8080/bien-categoria/all) (http://localhost:8080/bien-categoria/all)
