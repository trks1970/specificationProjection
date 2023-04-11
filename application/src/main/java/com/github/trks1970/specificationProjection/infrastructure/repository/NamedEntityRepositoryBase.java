package com.github.trks1970.specificationProjection.infrastructure.repository;

import com.github.trks1970.specificationProjection.domain.model.Named;
import com.github.trks1970.specificationProjection.domain.repository.NamedTypeRepository;
import com.github.trks1970.specificationProjection.infrastructure.entity.NamedEntity;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public abstract class NamedEntityRepositoryBase<
        ID extends Serializable, T extends Named<ID>, E extends NamedEntity<ID>>
    extends PersistentEntityRepositoryBase<ID, T, E> implements NamedTypeRepository<ID, T> {

  protected abstract Specification<E> name(String name);

  @Override
  public Set<T> findByName(String name) {
    log.trace("{} finding by name {}", getClass().getName(), name);
    return repository().findAll(name(name)).stream()
        .map(this::toDomain)
        .collect(Collectors.toSet());
  }
}
