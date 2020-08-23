#!/usr/bin/env bash

set -e
set -o pipefail

docker volume create --name gradle-6.4.1-jdk11

docker run --rm \
    -v gradle-6.0.1-jdk11:/home/gradle/.gradle \
    -v $PWD:/app \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -w /app \
    gradle:6.4.1-jdk11 \
    gradle $@