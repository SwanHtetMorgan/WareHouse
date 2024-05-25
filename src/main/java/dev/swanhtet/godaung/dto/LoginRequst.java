package dev.swanhtet.godaung.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequst {

	@NotNull(message = "message cannot be null")
	@NotBlank(message = "email cannot be blank")
	@Email
	String email;

	@NotNull(message = "password must be filled")
	@NotBlank(message = "password must be filled")
	String password;

}
