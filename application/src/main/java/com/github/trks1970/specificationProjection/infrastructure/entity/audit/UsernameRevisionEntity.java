package com.github.trks1970.specificationProjection.infrastructure.entity.audit;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@Table(name = "revision")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RevisionEntity(UsernameRevisionListener.class)
@SuppressFBWarnings(
    value = {
      "NP_NONNULL_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR",
      "UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD"
    },
    justification = "JPA Entity")
public class UsernameRevisionEntity {
  @Id
  @SequenceGenerator(
      name = "seq_revision",
      sequenceName = "seq_revision",
      initialValue = 1000,
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_revision")
  @Column(name = "id", nullable = false, updatable = false)
  @RevisionNumber
  private Long id;

  @Version
  @Column(name = "version")
  private Integer version;

  @RevisionTimestamp
  @EqualsAndHashCode.Include
  @Column(name = "timestamp", nullable = false)
  private long timestamp;

  @EqualsAndHashCode.Include
  @Column(name = "user_name", nullable = false)
  private String userName;
}
