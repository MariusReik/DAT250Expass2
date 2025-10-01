### Expass 5 - Redis 

## Installation
I used Redis (locally installed) and verified that it worked with `PING` in the CLI.  
I also confirmed it started correctly on port 6379.

CLI Experiments
- Tested `SET` and `GET` to store and retrieve key-value pairs.
- Used `EXPIRE` and `TTL` to experiment with time-to-live on keys.
- Implemented logged-in user tracking with `SADD`, `SREM`, and `SMEMBERS`.
- Represented a poll with `HSET` and updated votes with `HINCRBY`.

## Java Client
I used Jedis (`UnifiedJedis`) to connect to Redis and repeated the CLI experiments in Java code.  
This included:
- Key-value storage
- TTL expiration
- Logged-in users with sets
- Poll votes with hashes

## Cache Implementation
I implemented a `PollCache` class that:
- Stores poll results in Redis using `HSET`
- Retrieves polls from Redis if cached, otherwise simulates a DB fetch
- Updates the poll in Redis with `HINCRBY` when a new vote is cast
- Uses a TTL for cache invalidation  

## Other expass assigmnets
- Can be found in the same github repo