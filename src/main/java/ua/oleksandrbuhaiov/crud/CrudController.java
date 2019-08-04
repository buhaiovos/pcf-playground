package ua.oleksandrbuhaiov.crud;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/person")
public class CrudController {
    private final Map<UUID, Person> repository = new HashMap<>();

    @GetMapping
    public List<Person> getAll() {
        return new ArrayList<>(repository.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable UUID id) {
        return Optional.of(repository.get(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        final String firstName = requireNonNull(person.firstName);
        final String lastName = requireNonNull(person.lastName);
        final UUID id = UUID.randomUUID();

        Person newPerson = new Person()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName);

        repository.put(id, newPerson);

        return ResponseEntity
                .created(URI.create(format("/person/%s", id)))
                .body(newPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable UUID id, @RequestBody Person person) {
        Person existing = repository.get(id);
        if (existing != null) {
            existing.firstName = requireNonNull(person.firstName);
            existing.lastName = requireNonNull(person.lastName);

            return ResponseEntity.ok(existing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repository.remove(id);
    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(content = JsonInclude.Include.NON_NULL)
    static class Person {
        private UUID id;
        private String firstName;
        private String lastName;
    }
}
