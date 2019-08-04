package ua.oleksandrbuhaiov.message;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/messages")
@Validated
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository repo;

    @GetMapping
    public List<Message> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Message createMessage(@RequestBody @Valid Message message) {
        return repo.save(message);
    }
}
