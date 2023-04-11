package com.github.trks1970.specificationProjection.infrastructure.mapper;

import com.github.trks1970.specificationProjection.domain.model.Persistent;
import com.github.trks1970.specificationProjection.infrastructure.entity.PersistentEntity;
import java.io.Serializable;

public interface EntityMapper<
    ID extends Serializable, D extends Persistent<ID>, E extends PersistentEntity<ID>> {
  D toDomain(E entity);

  E toEntity(D type);

  E toEntity(D type, E entity);
}
