# Project Details
The project consists of 4 microservices.
- Mobilab-service
- ECR-service
- Naming-server
- Api-gateway

## Deployment
For deploying on the Dev enviroment run the above command on the Project folder:<br>
```bash
docker-compose up --build
```
By this command with help of **docker-compose.yml** file, all the microservice will deploy.

## URL Guide
- Mobilab-Service: <link>http://localhost:8765/MOBILAB-SERVICE/api</link>
- Mobilab-Service-Swagger: <link>http://localhost:8765/MOBILAB-SERVICE/swagger-ui.html</link>
- ECR-Service: <link>http://localhost:8765/ECR-SERVICE/api</link>
- ECR-Service-Swagger: <link>http://localhost:8765/ECR-SERVICE/swagger-ui.html</link>
- Naming-Server: <link>http://localhost:8761/</link>
- API-Gateway: <link>http://localhost:8765/</link>
- Zipkin-Service: <link>http://localhost:9411/</link>

