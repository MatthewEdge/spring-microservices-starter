#!/bin/sh

ROOT_DIR=$PWD

cd ./gateway
./gradlew build
cd ${ROOT_DIR}

cd ./users
./gradlew build
cd ${ROOT_DIR}

docker-compose build --parallel
docker-compose up -d
