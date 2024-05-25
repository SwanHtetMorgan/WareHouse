package dev.swanhtet.godaung.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/** DTO for {@link dev.swanhtet.godaung.model.Role} */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

  @NotNull String roleName;
}
