package no.hvl.pollapp.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PollEventListener {

    @RabbitListener(queues = "#{pollQueue.name}")
    public void receiveMessage(String message) {
        System.out.println(" [x] Received: " + message);
    }
}