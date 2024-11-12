
# personapp-hexa-spring-boot
**Plantilla de laboratorio para Arquitectura Hexagonal en Spring Boot**

Este proyecto es una plantilla para laboratorios de arquitectura hexagonal utilizando Spring Boot. Integra dos adaptadores de entrada: una API REST documentada con Swagger y una CLI (Interfaz de Línea de Comandos), cada uno implementado como una aplicación independiente. La aplicación está diseñada para utilizar bases de datos MongoDB y MariaDB en contenedores Docker.

### Prerrequisitos
- **Java JDK 11** instalado
- **Docker y Docker Compose** instalados
- **Lombok** configurado en tu IDE (IntelliJ IDEA, Eclipse, etc.)

### Bases de Datos en Contenedores
1. **MariaDB**: La base de datos MariaDB se despliega en un contenedor Docker y expone el puerto `3306`.
2. **MongoDB**: MongoDB también se despliega en un contenedor Docker y expone el puerto `27017`.

### Estructura del Proyecto
- **application**: Contiene la lógica de negocio y el modelo de dominio.
- **rest-input-adapter**: Adaptador para las solicitudes HTTP, documentado con Swagger en [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
- **cli-input-adapter**: Adaptador CLI que se conecta a las bases de datos. **Nota**: El adaptador CLI no se encuentra dockerizado.
- **maria-output-adapter y mongo-output-adapter**: Adaptadores de salida para las bases de datos MariaDB y MongoDB, respectivamente.

### Despliegue con Docker
1. Clona el repositorio y navega a la carpeta raíz del proyecto.
2. Ejecuta el comando `docker-compose up --build` para construir y levantar los contenedores.

    ```bash
    docker-compose up --build
    ```

3. Verifica que los contenedores estén corriendo:
   - **API REST** en [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
   - **MongoDB** en el puerto `27017`
   - **MariaDB** en el puerto `3306`

### Configuración de Base de Datos
- Los archivos `.js` en la carpeta `scripts` contienen los scripts de inicialización para MongoDB.
- El archivo `docker-compose.yml` configura automáticamente las bases de datos y las conexiones.

### Ejecución de la API REST
- La API REST corre en el puerto `8080`.
- Swagger UI está disponible en [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

### Ejecución del CLI
Para ejecutar el CLI, utiliza el archivo `PersonAppCli.java` ubicado en `cli-input-adapter/src/main/java/co/edu/javeriana/as/personapp/`.

Esto iniciará la aplicación CLI y te mostrará un menú interactivo en el cual puedes navegar usando los números del teclado:

```
1 para trabajar con el Modulo de Personas
2 para trabajar con el Modulo de Profesiones
3 para trabajar con el Modulo de Telefonos
4 para trabajar con el Modulo de Estudios
0 para Salir
Ingrese una opción:
```
