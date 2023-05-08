# Mobilab-Service (Bank Account Management)
The Mobilab-service is responsible for managing customer bank accounts and money transformation between them with help of the ECR-service microservice.

## Entities
- Account
- Transaction

## Tech Stack
- **JAVA** = version 17
- **Spring boot framework** = 3.0.6
- **Database** = MYSQL


## Deployment
By the above command, ecr-service will deploy on:<br>
<link>http://localhost:9080/api</link>

```bash
  docker-compose up --build
```

## Documentation
- Swagger documention will be deploy on: <link>http://localhost:9080/swagger-ui.html</link>
- Postman collection will be available on project folder.
```

## Run Test cases
Run project test cases via maven

```bash
  mvn test
```  

## Run Locally
Go to the project directory and install dependencies.

```bash
  ./mvnw clean pacakge
```

### Environment Variables
To run this project without docker localy, you will need to add your local machine environment variables to application-local.properties file and dont forget to change the profile.

