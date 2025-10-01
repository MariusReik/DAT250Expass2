package no.hvl.dat250.expass5;

import redis.clients.jedis.UnifiedJedis;
import java.util.Map;
import java.util.HashMap;

public class PollCache {
    private final UnifiedJedis jedis;
    private final int ttlSeconds;

    public PollCache(String redisUri, int ttlSeconds) {
        this.jedis = new UnifiedJedis(redisUri);
        this.ttlSeconds = ttlSeconds;
    }

    // Simulates fetching poll data from DB
    private Map<String, String> fetchFromDatabase(String pollId) {
        Map<String, String> dbResult = new HashMap<>();
        dbResult.put("votes:yes", "269");
        dbResult.put("votes:no", "268");
        dbResult.put("votes:meh", "42");
        return dbResult;
    }

    // Get poll results, prefer cache
    public Map<String, String> getPoll(String pollId) {
        String key = "poll:" + pollId;
        if (jedis.exists(key)) {
            System.out.println("Cache hit for poll " + pollId);
            return jedis.hgetAll(key);
        } else {
            System.out.println("Cache miss for poll " + pollId + ", loading from DB...");
            Map<String, String> dbResult = fetchFromDatabase(pollId);
            jedis.hset(key, dbResult);
            jedis.expire(key, ttlSeconds);
            return dbResult;
        }
    }

    // Register a vote
    public void vote(String pollId, String option) {
        String key = "poll:" + pollId;
        jedis.hincrBy(key, "votes:" + option, 1);
        System.out.println("Vote recorded for option: " + option);
    }

    public void close() {
        jedis.close();
    }
}
