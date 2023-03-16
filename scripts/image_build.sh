#!/bin/bash

docker build . -t basheim/auth-service
docker push basheim/auth-service:latest