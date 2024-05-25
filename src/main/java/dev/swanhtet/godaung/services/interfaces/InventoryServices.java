package dev.swanhtet.godaung.services.interfaces;

import dev.swanhtet.godaung.dto.request.InventoryDto;
import dev.swanhtet.godaung.model.Inventory;
import dev.swanhtet.godaung.model.Product;
import java.util.List;

public interface InventoryServices {
  void registerInventory(InventoryDto inventoryDto);

  void addProductToInventory(Integer productID, Integer InventoryId);

  List<Product> getProductInInventory(Integer id);

  List<Inventory> getRegisteredInventory();
}
