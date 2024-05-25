package dev.swanhtet.godaung.repo;

import dev.swanhtet.godaung.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {}
