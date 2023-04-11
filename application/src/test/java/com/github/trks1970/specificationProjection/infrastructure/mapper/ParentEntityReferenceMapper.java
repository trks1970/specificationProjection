package com.github.trks1970.specificationProjection.infrastructure.mapper;

import com.github.trks1970.specificationProjection.infrastructure.entity.ParentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParentEntityReferenceMapper extends ReferenceMapper<Long, ParentEntity> {
  @PersistenceContext private final EntityManager entityManager;

  @Override
  protected EntityManager entityManager() {
    return entityManager;
  }

  @Override
  protected Class<ParentEntity> entityClass() {
    return ParentEntity.class;
  }
}
