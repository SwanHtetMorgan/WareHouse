package dev.swanhtet.godaung.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Role", schema = "GoDaung")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id", nullable = false)
  private Integer id;

  @Column(name = "role_name", nullable = false, unique = true, length = 50)
  private String roleName;

  @OneToMany(mappedBy = "role", cascade = CascadeType.DETACH, orphanRemoval = true)
  private List<User> users;
}
