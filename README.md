## README
### Proyecto para prueba tecnica
Author Jaime Cenzano (JPCenz)
#### Tecnologias
- JDK 17
- Spring Boot 3.3.0
- Maven 
- H2 Database
- Docker
- WebFlux

#### Instrucciones
1. Descargar la imagen de docker
```shell 
Docker pull jpcenzano/pruebatecnicatcs:latest
```
2. Ejecutar la imagen(escoger el puerto que desee exponer)
```shell
Docker run -p 8080:8080 jpcenzano/pruebatecnicatcs:latest
```
![img_1.png](img_1.png)

3. Crear un usuario
```shell
http://localhost:8080/api/auth/create
```

![img.png](img.png)

4. Iniciar sesion para generar token
```shell
http://localhost:8080/api/auth/login
```
![img_2.png](img_2.png)

5. Consumir los servicios
```shell
POST http://localhost:8080/api/exchange/convert
GET http://localhost:8080/api/exchange/history
```
Colocar el Authorization en el header con el token generado
![img_3.png](img_3.png)

6. Pruebas de los servicios

Convertir moneda
![img_4.png](img_4.png)

Listar Registros
![img_5.png](img_5.png)

Manejo de Errores
![img_6.png](img_6.png)
![img_7.png](img_7.png)
