package dev.swanhtet.godaung.controller;

import dev.swanhtet.godaung.dto.request.InventoryDto;
import dev.swanhtet.godaung.services.impl.InventoryServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory", description = "Operations related to inventory management")
@Slf4j
public class InventoryController {

  private final InventoryServiceImpl inventoryService;

  @PostMapping("")
  @Tag(name = "Inventory")
  public ResponseEntity<?> registerInventory(@Validated @RequestBody InventoryDto inventoryDto) {
    inventoryService.registerInventory(inventoryDto);
    return ResponseEntity.ok().body("Created successfully");
  }

  @PostMapping("/{inventroyId}/product/{productID}")
  public ResponseEntity<?> addProductToInventory(
      @PathVariable Integer productID, @PathVariable Integer inventroyId) {
    inventoryService.addProductToInventory(productID, inventroyId);
    return ResponseEntity.ok().body("Add To the Inventory");
  }

  @GetMapping("")
  public ResponseEntity<?> addProductToInventory() {
    return ResponseEntity.ok().body(inventoryService.getRegisteredInventory());
  }

  @GetMapping("/id/{inventoryId}")
  public ResponseEntity<?> getAllProductsInInventory(@PathVariable Integer inventoryId) {

    return ResponseEntity.ok().body(inventoryService.getProductInInventory(inventoryId));
  }
}
