#!/usr/bin/env bash

set -e
set -o pipefail

docker volume create --name golang

docker run --rm \
    -v $PWD/weather-api-mock:/app \
    -w /app \
    golang go $@