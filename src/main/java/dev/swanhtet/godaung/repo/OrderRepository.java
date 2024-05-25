package dev.swanhtet.godaung.repo;

import dev.swanhtet.godaung.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
