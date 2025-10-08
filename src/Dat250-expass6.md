# DAT250 Experiment Assignment 6

## Goal
The goal of this experiment was to extend the existing PollApp with event sourcing functionality using a message broker.  
I chose **RabbitMQ** (AMQP protocol) since it is well-documented and easy to integrate with Spring Boot.

## Implementation
- Added RabbitMQ dependency (`spring-boot-starter-amqp`).
- Created `RabbitConfig.java` to configure an exchange and queue.
- Implemented `PollEventPublisher` to send events when polls are created or votes are cast.
- Implemented `PollEventListener` to receive and log events.
- Updated `PollController` to publish messages on poll creation and voting.
- Added RabbitMQ connection settings to `application.properties`.

When a new poll or vote is registered, the app now publishes a message to RabbitMQ.  
The listener prints incoming messages to the console, confirming that event sourcing works.

## Technical issues
- Initial connection failed due to an Erlang cookie mismatch between system and user accounts.
- Solved by aligning `.erlang.cookie` files and renaming `.erlang.cookie.txt` to `.erlang.cookie`.
- After fixing, RabbitMQ connected and worked as expected