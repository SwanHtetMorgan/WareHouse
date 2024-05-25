package dev.swanhtet.godaung.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Value;

/** DTO for {@link dev.swanhtet.godaung.model.User} */
@Value
public class UserDto implements Serializable {

  @NotNull(message = "username must not be null data")
  @NotBlank(message = "username must not be blank")
  String username;

  @NotNull(message = "Password must not be null data")
  @NotBlank(message = "Password must not be blank")
  String password;

  @NotNull(message = "Email must not be null data")
  @NotBlank(message = "Email must not be blank")
  @Email
  String email;

  @NotNull(message = "Role Must be filled ")
  Integer roleId;
}
