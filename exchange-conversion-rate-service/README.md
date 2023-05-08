# ECR-service (Exchange Conversion Rate)
The ECR-service is responsible for calculating the exchange conversion rate between two currencies (v1 just supports USD and EUR) with the use of third parties or its own offline data. 

## Entities
- CurrencyPair

## Tech Stack
- **JAVA** = version 17
- **Spring boot framework** = 3.0.6
- **Database** = MYSQL


## Deployment
By the above command, ecr-service will deploy on:<br>
<link>http://localhost:9070/api</link>

```bash
  docker-compose up --build
```

## Documentation
- Swagger documention will be deploy on: <link>http://localhost:9070/swagger-ui.html</link>
- Postman collection will be available on project folder.

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

