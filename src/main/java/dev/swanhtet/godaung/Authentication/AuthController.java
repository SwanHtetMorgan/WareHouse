package dev.swanhtet.godaung.Authentication;

import dev.swanhtet.godaung.dto.LoginRequst;
import dev.swanhtet.godaung.dto.request.UserDto;
import dev.swanhtet.godaung.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final UserServiceImpl userService;

	@PostMapping("/register")
	@Tag(name = "Authentication", description = "Register User")
	public ResponseEntity<?> registerUser(@Validated @RequestBody UserDto userDto) {
		boolean condition = userService.createUser(userDto);
		return  (condition) ? ResponseEntity.ok().body("registration Successful") : ResponseEntity.badRequest().body("Failed to register");
	}

	@PostMapping("/authenticate")
	@Tag(name = "Authentication", description = "Authenticate User")
	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginRequst loginRequst) {
		String token  = userService.authenticateUser(loginRequst);
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("Token", token);
		return ResponseEntity.ok().body(tokenMap);
	}

}