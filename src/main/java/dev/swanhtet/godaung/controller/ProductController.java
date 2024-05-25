package dev.swanhtet.godaung.controller;

import dev.swanhtet.godaung.dto.request.ProductDto;
import dev.swanhtet.godaung.services.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductServiceImpl productService;

  @PostMapping("/")
  @Tag(name = "Product")
  public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
    productService.createProduct(productDto);
    return ResponseEntity.ok().body("Created");
  }

  @GetMapping("")
  @Tag(name = "Product")
  public ResponseEntity<?> getAllProduct() {
    return ResponseEntity.ok().body(productService.getAllPrdouct());
  }

  @GetMapping("/{productId}")
  @Tag(name = "Product")
  public ResponseEntity<?> getProductById(@PathVariable Long productId) {
    return ResponseEntity.ok().body(productService.getProductById(productId));
  }

  @PutMapping("/{productId}")
  @Tag(name = "Product")
  public ResponseEntity<?> updateProduct(
      @PathVariable Long productId, @RequestBody ProductDto productDto) {
    boolean condition = productService.updateProduct(productId, productDto);
    if (condition) {
      return ResponseEntity.ok().body("Updated successfully");
    }
    return ResponseEntity.badRequest().body("Failed to update");
  }

  @DeleteMapping("/{productId}")
  @Tag(name = "Product")
  public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
    boolean condtion = productService.deleteProduct(productId);
    if (condtion) {
      return ResponseEntity.ok().body("Deleted SuccessFully");
    }
    return ResponseEntity.badRequest().body("Failed to delete");
  }

  @PutMapping("/{productId}/inventory/{inventoryId}")
  @Tag(name = "Product")
  public ResponseEntity<?> assignToProduct(
      @PathVariable Integer productId, @PathVariable Integer inventoryId) {
    return ResponseEntity.ok().body(productService.assignToInventory(productId, inventoryId));
  }

  @GetMapping("/inventory/{inventoryId}")
  @Tag(name = "Product", description = "Find the product by the inventoryId ")
  public ResponseEntity<?> getProductByInventoryId(@PathVariable Integer inventoryId) {
    return ResponseEntity.ok().body(productService.findProductByInventory(inventoryId));
  }
}
