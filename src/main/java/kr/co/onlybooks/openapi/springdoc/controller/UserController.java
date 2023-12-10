package kr.co.onlybooks.openapi.springdoc.controller;

import kr.co.onlybooks.openapi.springdoc.dto.UserIn;
import kr.co.onlybooks.openapi.springdoc.dto.UserOut;
import kr.co.onlybooks.openapi.springdoc.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private Map<String, User> db = new HashMap<>();

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody UserIn userIn) {
        String id = UUID.randomUUID().toString();

        db.put(id, userIn.toEntity(id));

        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOut> findUser(@PathVariable("id") String id) {
        User user = db.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(UserOut.fromEntity(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOut> updateUser(@PathVariable("id") String id,
                                              @RequestBody UserIn userIn) {
        User user = db.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        db.put(id, userIn.toEntity(id));

        return ResponseEntity.ok(UserOut.fromEntity(db.get(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        User user = db.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        db.remove(id);

        return ResponseEntity.noContent().build();
    }
}
