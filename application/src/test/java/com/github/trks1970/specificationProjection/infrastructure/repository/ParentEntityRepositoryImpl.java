package com.github.trks1970.specificationProjection.infrastructure.repository;

import com.github.trks1970.specificationProjection.domain.exception.NotFoundException;
import com.github.trks1970.specificationProjection.domain.model.Parent;
import com.github.trks1970.specificationProjection.domain.model.Persistent;
import com.github.trks1970.specificationProjection.domain.repository.ParentRepository;
import com.github.trks1970.specificationProjection.infrastructure.entity.ChildEntity_;
import com.github.trks1970.specificationProjection.infrastructure.entity.IdProjection;
import com.github.trks1970.specificationProjection.infrastructure.entity.ParentEntity;
import com.github.trks1970.specificationProjection.infrastructure.entity.PersistentEntity;
import com.github.trks1970.specificationProjection.infrastructure.mapper.EntityMapper;
import com.github.trks1970.specificationProjection.infrastructure.mapper.ParentEntityMapper;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.JpaBaseRepository;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.JpaChildEntityRepository;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.JpaParentEntityRepository;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.specification.ChildEntitySpecification;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.specification.ParentEntitySpecification;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParentEntityRepositoryImpl
    extends NamedEntityRepositoryBase<Long, Parent, ParentEntity> implements ParentRepository {

  private final JpaParentEntityRepository jpaParentEntityRepository;
  private final JpaChildEntityRepository jpaChildEntityRepository;
  private final ParentEntityMapper parentEntityMapper;

  @Override
  public void deleteById(Long id) {
    log.trace("deleteById enter");
    log.trace("fetching idProjections of children, query should only contain 'id'");
    Set<IdProjection> children =
        new HashSet<>(
            jpaChildEntityRepository.findBy(
                ChildEntitySpecification.parent(id),
                fetchableFluentQuery ->
                    fetchableFluentQuery.as(IdProjection.class).project(ChildEntity_.ID).all()));
    log.trace("deleting children");
    jpaChildEntityRepository.deleteAllById(
        children.stream().map(IdProjection::getId).collect(Collectors.toSet()));
    log.trace("deleting parent");
    jpaParentEntityRepository.deleteById(id);
    log.trace("deleteById exit");
  }

  @Override
  protected JpaBaseRepository<ParentEntity, Long, Long> repository() {
    return jpaParentEntityRepository;
  }

  @Override
  protected EntityMapper<Long, Parent, ParentEntity> mapper() {
    return parentEntityMapper;
  }

  @Override
  @SuppressWarnings("unused")
  protected NotFoundException notFoundException(
      @Nullable Long id, @Nullable Persistent<Long> type, @Nullable PersistentEntity<Long> entity) {
    return new NotFoundException(Parent.class, "id " + id);
  }

  @Override
  protected Specification<ParentEntity> name(String name) {
    return ParentEntitySpecification.name(name);
  }
}
