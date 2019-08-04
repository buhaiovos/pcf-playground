package ua.oleksandrbuhaiov.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/number")
@RequiredArgsConstructor
public class NumberConsumer {
    private final RestTemplate web;
    @Value("${NUMBER_URL:'http://localhost:8081/random-number'}")
    private String url;

    @GetMapping
    public String getNumber() {
        return "I've got " + web.getForObject(url, Integer.class);
    }
}
