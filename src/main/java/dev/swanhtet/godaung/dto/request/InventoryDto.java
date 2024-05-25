package dev.swanhtet.godaung.dto.request;

import java.io.Serializable;
import lombok.Value;

/** DTO for {@link dev.swanhtet.godaung.model.Inventory} */
@Value
public class InventoryDto implements Serializable {

  Integer quantity;
  String location;
}
