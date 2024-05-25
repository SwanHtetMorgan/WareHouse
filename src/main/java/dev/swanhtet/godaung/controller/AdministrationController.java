package dev.swanhtet.godaung.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdministrationController {

  @GetMapping("")
  public ResponseEntity<?> getAdminMessage() {
    return ResponseEntity.ok().body("Hello Admin");
  }
}
