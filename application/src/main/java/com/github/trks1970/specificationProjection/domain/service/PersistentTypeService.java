package com.github.trks1970.specificationProjection.domain.service;

import com.github.trks1970.specificationProjection.domain.model.Persistent;
import com.github.trks1970.specificationProjection.domain.repository.PersistentTypeRepository;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

public interface PersistentTypeService<ID extends Serializable, T extends Persistent<ID>> {
  PersistentTypeRepository<ID, T> repository();

  @Transactional
  default T save(@Valid T persistentType) {
    return repository().save(persistentType);
  }

  @Transactional
  default T findById(ID id) {
    return repository().findById(id);
  }

  @Transactional
  default List<T> findAllById(Set<ID> ids) {
    return repository().findAllById(ids);
  }

  @Transactional
  default void deleteById(ID id) {
    repository().deleteById(id);
  }
}
