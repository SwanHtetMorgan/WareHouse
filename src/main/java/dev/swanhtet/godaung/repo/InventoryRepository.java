package dev.swanhtet.godaung.repo;

import dev.swanhtet.godaung.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

  // Object loaded
  @Query(
      value =
          "select  * from GoDaung.product inner join  GoDaung.inventory on"
              + " GoDaung.inventory.inventory_id = product.inventory_id",
      nativeQuery = true)
  Object findProductInInventory();
}
