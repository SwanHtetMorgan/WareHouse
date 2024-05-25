package dev.swanhtet.godaung.services.impl;

import dev.swanhtet.godaung.dto.ProductResponseDto;
import dev.swanhtet.godaung.dto.request.ProductDto;
import dev.swanhtet.godaung.model.Inventory;
import dev.swanhtet.godaung.model.Product;
import dev.swanhtet.godaung.repo.InventoryRepository;
import dev.swanhtet.godaung.repo.ProductRepository;
import dev.swanhtet.godaung.services.interfaces.ProductServices;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductServices {

  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;
  private final InventoryRepository inventoryRepository;

  @Override
  public void createProduct(@Valid ProductDto productDto) {
    try {
      productRepository.save(modelMapper.map(productDto, Product.class));
      return;
    } catch (DataException e) {
      log.error(e.getErrorMessage());
    }
  }

  @Override
 // @Cacheable(value = "products")
  public List<ProductResponseDto> getAllPrdouct() {
    log.info("call from database");
    return productRepository.findAll().stream()
        .map(product -> modelMapper.map(product, ProductResponseDto.class))
        .toList();
  }

  @Override
  @Cacheable(value = "products", key = "#productId")
  public ProductResponseDto getProductById(Long productId) {
    log.info("call from database");
    Optional<Product> optionalProduct = productRepository.findById(intConvertor(productId));
    if (optionalProduct.isPresent()) {
      Product product = optionalProduct.get();
      return modelMapper.map(product, ProductResponseDto.class);
    }
    return null;
  }

  @Override
  //@CachePut(value = "products", key = "#productId")
  public boolean updateProduct(Long productId, ProductDto productDto) {
    Optional<Product> optionalProduct = productRepository.findById(intConvertor(productId));
    if (optionalProduct.isPresent()) {
      Product existingProduct = optionalProduct.get();
      modelMapper.map(productDto, existingProduct);
      productRepository.save(existingProduct);
      return true;
    }
    return false;
  }

  @Override
//  @CacheEvict(value = "products", allEntries = true)
  public boolean assignToInventory(Integer inventoryId, Integer productId) {

    Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
    Optional<Product> optionalProduct = productRepository.findById(productId);
    if (optionalProduct.isPresent() && optionalInventory.isPresent()) {
      Product product = optionalProduct.get();
      Inventory inventory = optionalInventory.get();
      product.setInventory_id(inventory.getId());
      productRepository.save(product);
      return true;
    }
    return false;
  }

  @Override
  @CacheEvict(value = "products", key = "#productId")
  public boolean deleteProduct(Integer productId) {
    try {
      log.info("Deleted");
      productRepository.deleteById(productId);
      return true;
    } catch (DataAccessException e) {
      throw e;
    }
  }

  @Override
 // @Cacheable(value = "products", key = "#inventoryId")
  public List<ProductResponseDto> findProductByInventory(Integer inventoryId) {
    try {
      List<Product> products = productRepository.findProductByInventoryId(inventoryId);
      for (Product product : products) {
        log.info(product.toString());
      }
      return products.stream()
          .map(product -> modelMapper.map(product, ProductResponseDto.class))
          .toList();
    } catch (DataException e) {
      throw new RuntimeException(e.getErrorMessage());
    }
  }

  private Integer intConvertor(Long id) {
    return Integer.parseInt(String.valueOf(id));
  }
}
