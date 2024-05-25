package dev.swanhtet.godaung.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link dev.swanhtet.godaung.model.Role}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	@NotNull
	String roleName;

}