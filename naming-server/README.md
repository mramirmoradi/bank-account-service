# Naming-server (EUREKA)
The naming server is responsible for loud balancing and naming service between microservices.

## Tech Stack
- **JAVA** = version 17
- **Spring boot framework** = 3.0.6


## Deployment
By the above command, ecr-service will deploy on: <link>http://localhost:8761/</link>

```bash
  docker-compose up --build
```

## Run Locally
Go to the project directory and install dependencies.

```bash
  ./mvnw clean pacakge
```

### Environment Variables
To run this project without docker localy, you will need to add your local machine environment variables to application-local.properties file and dont forget to change the profile.

