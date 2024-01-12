#!/bin/bash

# Build the Spring Boot application Docker image
docker build -t baldurs-gate-3-api .

# Run Docker Compose to start the containers
docker-compose up -d