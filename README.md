# Five-0

[![Build Status](https://travis-ci.org/greatjapa/five-0.svg?branch=master)](https://travis-ci.org/greatjapa/five-0)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)](https://github.com/greatjapa/five-0/blob/master/LICENSE)

The word 'five-0' is a skate trick name. I'm a huge fan of skateboarding and when I started to develop open source projects, I decided to use skate tricks as project names.

## How to build

```sh
git clone https://github.com/greatjapa/five-0.git
cd five-0
make up
make load
```

## How to run

The `make up` command build the microservice code with maven and raise a docker container with it. As database, we use elasticsearch (see `docker-compose.yml`):

Crooked has 3 endpoints:
- GET  /connection?city=\<city\>&depth=\<depth\>
- GET  /time?city=\<city\>

As an example, you can access the microservice using `curl` command:
```sh
curl http://localhost:8080/connection?city=Rio%20de%20Janeiro&depth=5
curl http://localhost:8080/connection?city=Rio%20de%20Janeiro
curl http://localhost:8080/time?city=Rio%20de%20Janeiro
```