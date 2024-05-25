package dev.swanhtet.godaung.controller;

import dev.swanhtet.godaung.dto.request.UserDto;
import dev.swanhtet.godaung.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;

  @GetMapping
  @Tag(name = "USER")
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok().body(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  @Tag(name = "USER")
  public ResponseEntity<?> getUserById(@PathVariable Integer id) {
    try {
      return ResponseEntity.ok().body(userService.findById(id));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  @Tag(name = "USER")
  public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDto userDTO) {

    boolean condition = userService.updateUser(id, userDTO);
    if (condition) {
      return ResponseEntity.status(200).body("Updated User Successfully");
    }
    return ResponseEntity.badRequest().body("Failed to Update");
  }

  @DeleteMapping("/{id}")
  @Tag(name = "USER")
  public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
    boolean condition = userService.deleteUser(id);
    return (condition)
        ? ResponseEntity.ok().body("Deleted successfully ")
        : ResponseEntity.badRequest().body("Failed to deleted");
  }
}
