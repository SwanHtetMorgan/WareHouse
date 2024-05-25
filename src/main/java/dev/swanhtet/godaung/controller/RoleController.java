package dev.swanhtet.godaung.controller;

import dev.swanhtet.godaung.dto.RoleDto;
import dev.swanhtet.godaung.services.impl.RoleServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
	private  final RoleServiceImpl roleService;
	@PostMapping("/")
	@Tag(name = "Role", description = "new role creation")
	public ResponseEntity<?> roleCreation(@Valid @RequestBody RoleDto roleDto){
		if(roleDto.getRoleName() == null)
		{
			return ResponseEntity.badRequest().body("Null Data");
		}
		boolean condition = roleService.createNewRole(roleDto);
		if(condition){
			return 	ResponseEntity.ok().body("Role Creation Success");
		}
		return 		ResponseEntity.badRequest().body("Role Creation Failed");
	}

}
