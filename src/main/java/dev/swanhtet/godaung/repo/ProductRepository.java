package dev.swanhtet.godaung.repo;

import dev.swanhtet.godaung.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query(
      value = "SELECT * FROM GoDaung.product WHERE inventory_id = :inventoryId",
      nativeQuery = true)
  List<Product> findProductByInventoryId(@Param("inventoryId") Integer inventoryId);
}
