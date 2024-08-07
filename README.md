# My Microservices Project

## Overview

This project consists of three microservices:
- User Service
- Order Service
- Product Service

Each microservice is containerized using Docker and deployed using Kubernetes.

## Prerequisites

- Docker
- Kubernetes (Minikube or Docker Desktop with Kubernetes enabled)

## Build Docker Images

Build Docker images for each microservice:

```sh
cd userservice
docker build -t userservice:latest .

cd ../orderservice
docker build -t orderservice:latest .

cd ../productservice
docker build -t productservice:latest .
