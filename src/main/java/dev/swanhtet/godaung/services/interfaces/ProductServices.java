package dev.swanhtet.godaung.services.interfaces;

import dev.swanhtet.godaung.dto.ProductResponseDto;
import dev.swanhtet.godaung.dto.request.ProductDto;
import jakarta.validation.Valid;
import java.util.List;

public interface ProductServices {
  void createProduct(@Valid ProductDto productDto);

  List<ProductResponseDto> getAllPrdouct();

  ProductResponseDto getProductById(Long productId);

  boolean updateProduct(Long productId, ProductDto productDto);

  boolean assignToInventory(Integer inventoryId, Integer productId);

  boolean deleteProduct(Integer productId);

  List<ProductResponseDto> findProductByInventory(Integer inventoryId);
}
