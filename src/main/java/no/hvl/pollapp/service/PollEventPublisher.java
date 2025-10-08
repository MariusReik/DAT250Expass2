package no.hvl.pollapp.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PollEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PollEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishVoteEvent(String pollName, String voteOption) {
        String message = "Vote on poll: " + pollName + " -> " + voteOption;
        rabbitTemplate.convertAndSend("polls.exchange", "", message);
        System.out.println(" [x] Sent: " + message);
    }
}
