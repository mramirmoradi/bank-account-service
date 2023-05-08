# API-gatway service
The API gateway is responsible for managing requests that come to microservices with the help of the Naming server.

## Tech Stack
- **JAVA** = version 17
- **Spring boot framework** = 3.0.6


## Deployment
By the above command, ecr-service will deploy on: <link> http://localhost:8765/ </link>

```bash
  docker-compose up --build
```

## Run Locally
Go to the project directory and install dependencies.

```bash
  ./mvnw clean pacakge
```

### Environment Variables
To run this project without docker locally, you will need to add your local machine environment variables to application-local.properties file and dont forget to change the profile.

