# MusicStream

This project is an attempt to make a streaming music service.

## Technologies

### Backend

* Spring Boot
* Hibernate and Jpa as ORM (MySQL) and liquibase for the changelogs
* Lombok
* Mapstruct
* Junit and Mockito for the tests

### Frontend

* Html, css and js

## Installation

You will need [docker](https://www.docker.com/products/docker-desktop/) to run it.

In a command prompt, run the following (you must be in the project's folder):

```bash
docker compose up
```

The application will be available on port 8081. To stop it, in command prompt:

```bash
docker compose stop
```

Or just press CTRL-C in the command prompt used to start the container (you can also stop it in docker's gui).

## What's next

I plan to add playlists, add authentication through Keycloak and make a good frontend using React.
