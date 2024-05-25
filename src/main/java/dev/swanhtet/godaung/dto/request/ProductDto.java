package dev.swanhtet.godaung.dto.request;

import dev.swanhtet.godaung.model.CATEGORY;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Value;

/** DTO for {@link dev.swanhtet.godaung.model.Product} */
@Value
public class ProductDto implements Serializable {

  @NotNull @NotBlank String name;
  @NotNull @NotBlank String description;
  @NotNull CATEGORY category;
}
