# Identity Service

A Spring Boot application for user management, roles, permissions, JWT authentication, token creation, logout, refresh and Google login. Includes unit tests, Docker packaging, Docker Hub push, and AWS deployment.

## Features

- **User Management**: Create, update, delete, fetch users.
- **Roles & Permissions**: Assign roles/permissions to users.
- **JWT Auth**: Login, token creation, refresh, logout.
- **Google Login**: Authenticate users via Google OAuth2.
- **Unit Tests**: Test core functions with JUnit/Mockito.
- **Docker**: Package with Dockerfile, push to Docker Hub.
- **AWS**: Deploy on EC2.

## Requirements

- Java 17+
- Maven 3.8.x
- Docker
- AWS account
- Docker Hub account
- Google Cloud Console account (for Google OAuth2)

## Setup

### 1. Clone

```bash
git clone https://github.com/hieund18/identity-service.git
cd identity-service
```

### 2. Configure

Edit `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/identity_service
    username: your_username
    password: your_password
outbound:
  identity:
    client-id: your-google-client-id
    client-secret: your-google-client-secret
    redirect-uri: your-redirect-uri
```

### 3. Build

```bash
mvn clean install
```

### 4. Run Locally

```bash
mvn spring-boot:run
```

Access at `http://localhost:8080`.

## Unit Tests

Run tests in `src/test`:

```bash
mvn test
```

## Docker

### 1. Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/identity-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 2. Build & Push

```bash
docker build -t username/identity-service:latest .
docker push username/identity-service:latest
```

## AWS Deployment

### 1. ECS

- Create ECS Cluster.
- Define Task Definition with Docker image.
- Set up Service and Load Balancer.

### 2. EC2

- Launch EC2 instance (Ubuntu).
- Install Docker:

  ```bash
  sudo apt update
  sudo apt install docker.io
  sudo systemctl start docker
  ```

- Run image:

  ```bash
  docker pull username/identity-service:latest
  docker run -p 8080:8080 username/identity-service:latest
  ```

## Security

- Store JWT secret in environment variables or AWS Secrets Manager.
- Secure database with strong credentials.

## Contact

Email: hieund.forwork@gmail.com