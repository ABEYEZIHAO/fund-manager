#!/bin/bash

docker network create fundsys

docker stop fundsql
docker rm fundsql

docker stop fund-manager
docker rm fund-manager

docker run -d --name fundsql --network fundsys --network-alias mysql -e MYSQL_ROOT_PASSWORD=reven2010 -e MYSQL_DATABASE=fundsys mysql:8.0.26

sleep 5s
docker run -d --name fund-manager -dp 8090:8090 -w /app -v "$(pwd):/app" --network fundsys -e MYSQL_HOST=mysql -e MYSQL_USER=root -e MYSQL_PASSWORD=reven2010 -e MYSQL_DB=fundsys fund-manager



