# CHALLENGE-MERCADOLIBRE

Challenge-mercadolibre es una aplicación Backend Java que se desarrolló bajo unos requerimientos pero con cierta
libertad al desarrollador. Se entrega un microservicio REST que expone una API con 4 endpoints y nos retorna
en formato JSON la información solicitada con ciertas limitaciones ya que las APIS Publicas y su version free están muy 
limitadas y tomé la decision de usar unicamente "https://ipapi.com".

**NOTA**: Esta aplicación cuenta con base de datos H2 embebido y por su fácil integración con Spring Boot, 
cuenta con Cache Spring y OpenAPI Swagger embebidos. Lo anterior con el fin de generar un entregable más ligero 
y que cumpla con lo requerido. Bien sabemos que a nivel productivo no es recomendable y debemos excluir estos 
componentes y reemplazarlos con otras aplicaciones más robustas como PostgreSQL o MySQL, de igual manera para cache
con Redis Caché u otros.

## Instrucciones para la ejecuión.
**Requerimientos**: Se requiere tener instalado Docker Desktop y JDK 17

- Clonar o descargar la aplicación en el equipo.
- La aplicación contiene un archivo dockerfile en el que se ejecutarán los siguientes comandos en una terminal:
  1. docker build -t challenge .
  2. docker run -p 8080:8080 challenge
- una vez se haya creado la imagen y el contendor este corriendo podemos acceder a las siguientes URLs:
- `Database H2`: http://localhost:8080/challenge-mercadolibre/h2-console
  - URL: jdbc:h2:mem:testdb
  - username: challenge
  - password: challenge

Para la documentación y ejecución de la API podemos usar:
- `Swagger`: http://localhost:8080/challenge-mercadolibre/swagger-ui/index.html

O importar collection a Postman, pueden ubicar el archivo en la siguiente ruta del proyecto: 
- `PostmanCollection`: src\main\resources\postman\Challenge-Mercadolibre.postman_collection.json

### Para MeLi
Quiero agradecerles por brindarme la oportunidad de mostrarles mis capacidades. ¡fue un gran reto y me lo disfruté!
