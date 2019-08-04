package ua.oleksandrbuhaiov.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-message")
public class HelloController {
    @Value("${HELLO_MESSAGE:default}")
    private String helloMessage;

    @GetMapping
    public String getHelloMessage() {
        return this.helloMessage;
    }
}
