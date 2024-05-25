package dev.swanhtet.godaung.repo;

import dev.swanhtet.godaung.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
