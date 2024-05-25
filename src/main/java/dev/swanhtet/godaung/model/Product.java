package dev.swanhtet.godaung.model;

import dev.swanhtet.godaung.database.AuditTrail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Product", schema = "GoDaung")
public class Product extends AuditTrail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Lob
  @Column(name = "description")
  private String description;

  @Column(name = "category", length = 100)
  @Enumerated(EnumType.STRING)
  private CATEGORY category;

  private Integer inventory_id;
}
