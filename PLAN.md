## Current objective

Enable the Spring Boot application to run via `docker compose up -d` instead of a manual local startup.

## Checklist

- [x] Inspect current Spring Boot and Compose setup
- [x] Add container build files for the app
- [x] Add app service to Docker Compose with Kafka connectivity
- [x] Document container startup flow
- [x] Verify Gradle build and Compose config

## What changed

- Added a multi-stage [`Dockerfile`](/Users/thomaswintherbonderup/Development/audit-engine-java/Dockerfile) and [`.dockerignore`](/Users/thomaswintherbonderup/Development/audit-engine-java/.dockerignore) for the Spring Boot app image.
- Extended [`docker-compose.yml`](/Users/thomaswintherbonderup/Development/audit-engine-java/docker-compose.yml) with an `audit-api` service bound to port `8080` and wired to Kafka on the Compose network.
- Made Kafka bootstrap servers environment-overridable in [`src/main/resources/application.yml`](/Users/thomaswintherbonderup/Development/audit-engine-java/src/main/resources/application.yml) and documented the new flow in [`README.md`](/Users/thomaswintherbonderup/Development/audit-engine-java/README.md).

## Verification (2026-03-07, dockerize-app)

- PASS `./gradlew test`
- PASS `docker compose config`
- PASS `docker compose build audit-api`
- PASS `docker compose up -d`
- PASS `curl -sS -i http://127.0.0.1:8080/actuator/health`
