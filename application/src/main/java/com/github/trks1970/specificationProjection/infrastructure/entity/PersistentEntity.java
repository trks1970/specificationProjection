package com.github.trks1970.specificationProjection.infrastructure.entity;

import org.springframework.lang.Nullable;

public interface PersistentEntity<ID> {
  @Nullable
  ID getId();

  @Nullable
  Long getRevision();
}
