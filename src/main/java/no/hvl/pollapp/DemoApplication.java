package no.hvl.pollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DemoApplication {

    @GetMapping("/")
    public String hello() {
        return "Hello World, Jada";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}