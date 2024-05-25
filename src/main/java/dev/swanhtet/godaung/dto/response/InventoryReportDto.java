package dev.swanhtet.godaung.dto.response;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReportDto {
  private Map<String, Object> ResponseMap = new LinkedHashMap<>();
}
