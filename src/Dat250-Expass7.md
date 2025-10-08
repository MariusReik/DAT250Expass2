# DAT250 Experiment Assignment 7

## Goal
The goal of this experiment was to containerize the PollApp application using Docker.  
This makes the application easier to distribute and deploy together with RabbitMQ.

## Implementation
- Created a multi-stage `Dockerfile` using `gradle:8.5-jdk17` for building and `eclipse-temurin:17-jdk-jammy` for running.
- Built the image with `docker build -t pollapp .`.
- Ran both containers and verified communication between PollApp and RabbitMQ.

## Verification
- `docker ps` confirmed both containers were running.
- `curl http://localhost:8080/polls` returned `[]`, showing the API was live.
- RabbitMQ dashboard was accessible at `http://localhost:15672`.

## Technical issues
- RabbitMQ connection initially failed until both containers ran on the same network.

After fixes, PollApp and RabbitMQ ran successfully in Docker.
