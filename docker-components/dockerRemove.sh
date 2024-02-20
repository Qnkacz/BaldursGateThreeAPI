#!/bin/bash

docker stop bg3_api
docker stop bg3_api_db

docker rm bg3_api
docker rm bg3_api_db