package kr.co.onlybooks.openapi.springdoc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public class UserController implements UserResource {

    private Map<String, User> db = new HashMap<>();

    @PostMapping
    @Tag(name = "command")
    @Tag(name = "user-controller")
    @Operation(summary = "새 사용자를 등록한다")
    public ResponseEntity<Void> registerUser(@RequestBody UserIn userIn) {
        String id = UUID.randomUUID().toString();

        db.put(id, userIn.toEntity(id));

        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @GetMapping("/{id}")
    @Tag(name = "query")
    @Tag(name = "user-controller")
    @Operation(summary = "사용자를 조회한다")
    public ResponseEntity<UserOut> findUser(@PathVariable("id") String id) {
        User user = db.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(UserOut.fromEntity(user));
    }

    @PutMapping("/{id}")
    @Tag(name = "command")
    @Tag(name = "user-controller")
    @Operation(summary = "사용자 정보를 수정한다")
    @Override
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
    @Tag(name = "command")
    @Tag(name = "user-controller")
    @Operation(summary = "사용자를 삭제한다")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        User user = db.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        db.remove(id);

        return ResponseEntity.noContent().build();
    }
}
