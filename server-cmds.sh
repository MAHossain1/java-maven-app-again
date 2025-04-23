#!/usr/bin/env bash

export IMAGE_NAME=arman04/java-maven-app:$1

docker-compose -f docker-compose.yaml up --detach
echo "success"
export TEST=testvalue
