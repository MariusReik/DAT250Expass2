package no.hvl.dat250.expass5;

import java.util.Map;

public class RedisTest {
    public static void main(String[] args) {
        PollCache cache = new PollCache("redis://localhost:6379", 60);

        // First fetch -> miss, loads from DB
        Map<String, String> poll1 = cache.getPoll("1");
        System.out.println("Poll state = " + poll1);

        // Second fetch -> should hit cache
        Map<String, String> poll2 = cache.getPoll("1");
        System.out.println("Poll state (from cache) = " + poll2);

        // Record a vote
        cache.vote("1", "yes");

        // Fetch again -> updated cache
        Map<String, String> poll3 = cache.getPoll("1");
        System.out.println("Poll state after vote = " + poll3);

        cache.close();
    }
}
