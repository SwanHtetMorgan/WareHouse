package dev.swanhtet.godaung.dto;

import dev.swanhtet.godaung.model.CATEGORY;
import dev.swanhtet.godaung.model.Inventory;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link dev.swanhtet.godaung.model.Product} */
@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto {

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  Integer id;
  String name;
  String description;
  CATEGORY category;
  Inventory Inventory_id;
}
