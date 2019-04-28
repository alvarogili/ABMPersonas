# ABMPersonas
Herramienta ABM de personas

## Archivo de configuración

Si se desea cambiar la conexión a base de datos, crear un archivo con nombre "config.properties" en la carpeta "/opt/ABMPersonas/ y configurar la conexión de la base de datos. Un ejemplo de archio de configuración se muestra a continuación:

```sh
## Configuración de base de datos
## Motores soportados: PostgreSQL - SQLite

# Ejemplo PostgreSQl(La base de datos debe estar creada)
#database.url=jdbc:postgresql://localhost:5432/personas
#database.driver=org.postgresql.Driver
#database.username=postgres
#database.password=postgres
#database.hibernate.database=POSTGRESQL
#database.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
#database.hibernate.showSql=false

# H2
database.url=jdbc:h2:file:/tmp/h2
database.driver=org.h2.Driver
database.username=persona
database.password=
database.hibernate.database=
database.hibernate.dialect=org.hibernate.dialect.H2Dialect
database.hibernate.showSql=false
```
## Deploy en Tomcat local

Ejecutar los siguientes comandos:
```sh
cd ABMPersonas
mvn clean install
cp Personas-back/target/personas.war /var/lib/tomcat8/webapps
```

## Utilizar imagen docker

Generar imagen por unica vez:

```sh
docker build -t personas .
```

Luego generar el contenedor y ejecutarlo:

```sh
docker container run -it --publish 8081:8080 personas
```

o

```sh
docker run -d -p 8081:8080 personas
```
La diferencia entre el primero y el segundo es que, el primero ejcuta el contenedor y queda bloqueado logueando las acciones de tomcat, cuando se ejecuta `Ctrl+C` el contenedor se detiene y el segundo, ejeucta el contenedor en moo desatendido

## Archivo  ABMPersonas.postman_collection.json

Este archivo es un export de una colección de Postman(https://www.getpostman.com/), que permite ejecutar las acciones requeridas sobre la API.