package com.github.trks1970.specificationProjection.domain.service;

import com.github.trks1970.specificationProjection.domain.model.Named;
import com.github.trks1970.specificationProjection.domain.repository.NamedTypeRepository;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

public interface NamedTypeService<ID extends Serializable, T extends Named<ID>>
    extends PersistentTypeService<ID, T> {
  NamedTypeRepository<ID, T> repository();

  @Transactional
  default Set<T> findByName(@Valid String name) {
    return repository().findByName(name);
  }

  @Transactional
  default boolean isNameUnique(T namedType) {
    return repository().findByName(namedType.getName()).isEmpty();
  }
}
