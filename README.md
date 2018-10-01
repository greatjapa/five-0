# Five-0

[![Build Status](https://travis-ci.org/greatjapa/five-0.svg?branch=master)](https://travis-ci.org/greatjapa/five-0)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)](https://github.com/greatjapa/five-0/blob/master/LICENSE)

The word 'five-0' is a skate trick name. I'm a huge fan of skateboarding and when I started to develop open source projects, I decided to use skate tricks as project names.

## How to build

```sh
$ git clone https://github.com/greatjapa/five-0.git
$ cd five-0
$ make up
$ make load
```

## How to run

The `make up` command build the microservice code with maven and raise a docker container with it. As database, we use elasticsearch (see `docker-compose.yml`):

Five-0 has 2 endpoints:
- GET  /connection?city=\<city\>&depth=\<depth\>

As an example, you can try:
```sh
curl http://localhost:8080/connection?city=Rio%20de%20Janeiro
```
and the result will be:

```json
[
    {
        "city" : "Belo Horizonte",
        "steps" : 0
    },
    {
        "city" : "São Paulo",
        "steps" : 0
    },
    {
        "steps" : 1,
        "city" : "Campinas"
    },
    {
        "city" : "Trindade",
        "steps" : 2
    }
]

```

you also may define a max depth as argument:

```sh
curl http://localhost:8080/connection?city=Rio%20de%20Janeiro&depth=2
```

the result is:

```json
[
    {
        city: "Belo Horizonte",
        steps: 0
    },
    {
        city: "São Paulo",
        steps: 0
    },
    {
        city: "Campinas",
        steps: 1
    }
]
```

- GET  /time?city=\<city\>

The following example shows how this endpoint works:

```sh
curl http://localhost:8080/time?city=Rio%20de%20Janeiro
```

the result is:

```json
[
    {
        city: "Belo Horizonte",
        time: 2
    },
    {
        city: "São Paulo",
        time: 5
    },
    {
        city: "Campinas",
        time: 10
    },
    {
        city: "Trindade",
        time: 20
    }
]
```