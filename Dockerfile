# Stage 1: build
# Start with a maven image that includes JDK 21
FROM maven:3.9.8-amazoncorretto-21 AS build

# Copy source code and pom.xml file to /app folder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Buid source code with maven
RUN mvn package -DskipTests

# Stage 2: create image
# Start with amazon corretto JDK 21
FROM amazoncorretto:21.0.4

# Set working folder to App and copy complied file from above step
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# command to run application
ENTRYPOINT ["java", "-jar", "app.jar"]

