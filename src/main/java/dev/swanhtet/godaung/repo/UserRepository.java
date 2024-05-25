package dev.swanhtet.godaung.repo;

import dev.swanhtet.godaung.model.Role;
import dev.swanhtet.godaung.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  List<User> findByRole(Role role);
}
