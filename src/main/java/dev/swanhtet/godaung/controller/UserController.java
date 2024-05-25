package dev.swanhtet.godaung.controller;

import dev.swanhtet.godaung.dto.request.UserDto;
import dev.swanhtet.godaung.model.User;
import java.util.List;

import dev.swanhtet.godaung.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserServiceImpl userService;


  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    return null;
  }

  @GetMapping("/{id}")
  @Tag(name = "USER")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    return  null;
  }

  @PutMapping("/{id}")
  @Tag(name = "USER")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDTO) {
    return null;
  }

  @DeleteMapping("/{id}")
  @Tag(name = "USER")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    return null;
  }
}
