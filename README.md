# Project Details
The project consists of 4 microservices.
- **Mobilab-service**: The Mobilab-service is responsible for managing customer bank accounts and money transformation between them with help of the ECR-service microservice.
- **ECR-service**: The ECR-service is responsible for calculating the exchange conversion rate between two currencies (v1 just supports USD and EUR) with the use of third parties or its own offline data.
- **Naming-server**: The naming server is responsible for loud balancing and naming service between microservices.
- **Api-gateway**: The API gateway is responsible for managing requests that come to microservices with the help of the Naming server.


## Deployment
For deploying on the Dev environment run the above command on the Project folder:<br>
```bash
docker-compose up --build
```
By this command with help of **docker-compose.yml** file, all the microservice will deploy.

## URL Guide
- Mobilab-Service: <link> http://localhost:8765/MOBILAB-SERVICE/api </link>
- Mobilab-Service-Swagger: <link> http://localhost:9080/api/swagger-ui/index.html </link>
- ECR-Service: <link> http://localhost:8765/ECR-SERVICE/api </link>
- ECR-Service-Swagger: <link> http://localhost:9070/api/swagger-ui/index.html </link>
- Naming-Server: <link> http://localhost:8761/ </link>
- API-Gateway: <link> http://localhost:8765/ </link>
- Zipkin-Service: <link> http://localhost:9411/ </link>

