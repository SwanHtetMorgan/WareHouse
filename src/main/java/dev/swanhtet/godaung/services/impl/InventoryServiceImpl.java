package dev.swanhtet.godaung.services.impl;

import dev.swanhtet.godaung.dto.request.InventoryDto;
import dev.swanhtet.godaung.model.Inventory;
import dev.swanhtet.godaung.model.Product;
import dev.swanhtet.godaung.repo.InventoryRepository;
import dev.swanhtet.godaung.repo.ProductRepository;
import dev.swanhtet.godaung.services.interfaces.InventoryServices;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryServices {

  private final InventoryRepository inventoryRepository;
  private final ModelMapper modelMapper;
  private final ProductRepository productRepository;

  @Override
  public void registerInventory(InventoryDto inventoryDto) {
    try {
      inventoryRepository.save(modelMapper.map(inventoryDto, Inventory.class));
    } catch (EntityNotFoundException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public void addProductToInventory(Integer productId, Integer inventoryId) {
    Optional<Product> productOptional = productRepository.findById(productId);
    Optional<Inventory> inventoryOptional = inventoryRepository.findById(inventoryId);
    if (productOptional.isPresent() && inventoryOptional.isPresent()) {
      Product product = productOptional.get();
      Inventory inventory = inventoryOptional.get();
      //	product.setInventory(inventory);
      productRepository.save(product);
      inventoryRepository.save(inventory);
    } else {
      throw new IllegalArgumentException("Product or Inventory not found");
    }
  }

  @Override
  public List<Product> getProductInInventory(Integer id) {
    return productRepository.findProductByInventoryId(id);
  }

  @Override
  public List<Inventory> getRegisteredInventory() {
    return inventoryRepository.findAll();
  }
}
