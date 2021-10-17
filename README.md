# Applicación Control de Inventarios

#### Solución al reto técnico para solventar el siguiente problema:

Hacer una pequena app SpringBoot la cual permite :

* Realizar el control de inventarios de una empresa (stock), los bienes de la empresa se dividen en 3 categorías, la
  aplicación debe permitir añadir nuevos bienes a una determinada categoría y también poder dar de baja a estos.
* Varios tipos de bienes pueden estar dentro de la misma categoría.
* Se debe llevar el histórico de movimiento de los bienes.
* Se debe idear la mejor manera de implementar la solución para este caso.

#### La aplicación debe exponer endPoints para:

* Crear y actualizar categorias.
* Crear un nuevo bien y asignarlo a una categoría.
* Recuperar el listado de bienes con el detalle de estos (activos, dados de baja).
* Recuperar cuantos bienes disponibles existe en cada categoría.
* Recuperar las categorías existentes con sus respectivos bienes.
* Dada una categoria, recuperar cuando bienes están disponibles y cuantos se han dado de baja.
* Ingresar un lote de nuevos bienes.
* Dar de baja un bien o lote de bienes.

#### Indicaciones:

* Las clases y atributos a crear son a criterio del candidato evaluado, únicamente colocar los atributos que considere
  mas importantes teniendo como máximo 5 atributos por clase.
* La informacion debe ser almacenada en una base de datos  (
  puede utilizar la bdd que desee).
* Realizar las pruebas (unitarias, integración) que considere necesarias.
* Aplicar las practicas, patrones, estándares, etc que considere.
* Para las pantallas frontEnd, debe hacer uso de los endpoints creados en la app springBoot.
* Subir el código (front/back) a un repositorio GIT.

## Frameworks utilizados

* [Gradle](https://docs.gradle.org) - Gestor de dependencias
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)
  - Api de Persistencia
* [Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Flyway](https://flywaydb.org/) - Manejo y Control de migraciones
* [PostgreSQL](https://www.postgresql.org/) - Base de datos SQL
* [Lombok](https://projectlombok.org/) - Framework para reducir código mediante anotaciones
* [Github](https://github.com/) - Repositorio
* [Postman](https://www.postman.com/) - Test de endpoints

## Librerias necesarias

Para levantar la aplicación se debe contar con las siguientes versiones:

* JDK o OpenJDK v11+
* PostgreSQL v12+
* Gradle 7.2
* VS Code - IntelliJ ó cualquier editor de texto que soporte código Java.

## Levantar base de datos PostgreSQL

### Con Docker

Para levantar la aplicación es necesario tener instalado docker en su SO, para mas información de como realizar la
isntalación puede consultar acá:

* Windows: [Docker in Windows](https://docs.docker.com/desktop/windows/install/)
* Linux: [Docker in Linux](https://docs.docker.com/engine/install/ubuntu/)

```shell
$  docker run -p 5434:5432  --name postgres -e POSTGRES_PASSWORD=postgres -e  POSTGRES_DB=stock -d postgres:12-alpine
```

### Sin Docker

Para levantar la aplicacin es necesario tener instalo el sistema operativo:

* Windows: [Install PostgrSQL in Windows](https://www.postgresqltutorial.com/install-postgresql/)
*

Linux: [Install PostgrSQL in Linux](tion/idol/IDOL_12_0/MediaServer/Guides/html/English/Content/Getting_Started/Configure/_TRN_Set_up_PostgreSQL_Linux.htm)

* Tener instalado PostgreSQL en el puerto [5432].
* Se debe tener creada una base de datos con el nombre de [stock].

### Endpoints

* Listar todas las categorias: [GET](http://localhost:8080/categoria/all) (http://localhost:8080/categoria/all) - buscar
  todas las categorias sin referencias
* Listar categorias on
  referencias : [GET](http://localhost:8080/categoria/all-detail) (http://localhost:8080/categoria/all) - buscar todas
  las categorias sin referencias
* Buscar categoria por su nombre [GET](http://localhost:8080/categoria/all-detail) (http://localhost:8080/categori?name)
  - buscar todas las categorias si
*

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
