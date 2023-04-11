package com.github.trks1970.specificationProjection.infrastructure.repository;

import com.github.trks1970.specificationProjection.domain.exception.NotFoundException;
import com.github.trks1970.specificationProjection.domain.model.Child;
import com.github.trks1970.specificationProjection.domain.model.Persistent;
import com.github.trks1970.specificationProjection.domain.repository.ChildRepository;
import com.github.trks1970.specificationProjection.infrastructure.entity.ChildEntity;
import com.github.trks1970.specificationProjection.infrastructure.entity.PersistentEntity;
import com.github.trks1970.specificationProjection.infrastructure.mapper.ChildEntityMapper;
import com.github.trks1970.specificationProjection.infrastructure.mapper.EntityMapper;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.JpaBaseRepository;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.JpaChildEntityRepository;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.specification.ChildEntitySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChildEntityRepositoryImpl extends NamedEntityRepositoryBase<Long, Child, ChildEntity>
    implements ChildRepository {

  private final JpaChildEntityRepository jpaChildEntityRepository;
  private final ChildEntityMapper parentEntityMapper;

  @Override
  protected JpaBaseRepository<ChildEntity, Long, Long> repository() {
    return jpaChildEntityRepository;
  }

  @Override
  protected EntityMapper<Long, Child, ChildEntity> mapper() {
    return parentEntityMapper;
  }

  @Override
  @SuppressWarnings("unused")
  protected NotFoundException notFoundException(
      @Nullable Long id, @Nullable Persistent<Long> type, @Nullable PersistentEntity<Long> entity) {
    return new NotFoundException(Child.class, "id " + id);
  }

  @Override
  protected Specification<ChildEntity> name(String name) {
    return ChildEntitySpecification.name(name);
  }
}
