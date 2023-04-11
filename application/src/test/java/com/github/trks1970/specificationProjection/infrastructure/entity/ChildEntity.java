package com.github.trks1970.specificationProjection.infrastructure.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "child")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@SuppressFBWarnings(
    value = "NP_NONNULL_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
    justification = "JPA Entity")
public class ChildEntity implements NamedEntity<Long> {

  @Id
  @SequenceGenerator(
      name = "seq_child",
      sequenceName = "seq_child",
      initialValue = 1000,
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_child")
  @Column(name = "id", nullable = false, updatable = false)
  @Nullable
  private Long id;

  @Version
  @Column(name = "revision")
  @Nullable
  private Long revision;

  @Column(name = "name", unique = true, nullable = false)
  @EqualsAndHashCode.Include
  @NonNull
  private String name;

  @Column(name = "description")
  @Nullable
  private String description;

  @ManyToOne(optional = false)
  @NonNull
  private ParentEntity parent;
}
