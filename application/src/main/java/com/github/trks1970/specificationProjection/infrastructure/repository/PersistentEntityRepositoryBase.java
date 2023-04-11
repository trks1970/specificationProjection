package com.github.trks1970.specificationProjection.infrastructure.repository;

import com.github.trks1970.specificationProjection.domain.exception.NotFoundException;
import com.github.trks1970.specificationProjection.domain.model.Persistent;
import com.github.trks1970.specificationProjection.domain.repository.PersistentTypeRepository;
import com.github.trks1970.specificationProjection.infrastructure.entity.PersistentEntity;
import com.github.trks1970.specificationProjection.infrastructure.mapper.EntityMapper;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.JpaBaseRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Slf4j
public abstract class PersistentEntityRepositoryBase<
        ID extends Serializable, T extends Persistent<ID>, E extends PersistentEntity<ID>>
    implements PersistentTypeRepository<ID, T> {

  protected abstract JpaBaseRepository<E, ID, Long> repository();

  protected abstract EntityMapper<ID, T, E> mapper();

  @Override
  public T save(T item) {
    log.trace("Saving {}, {}", item.getClass().getName(), item);
    E itemEntity;
    if (item.getId() == null) {
      itemEntity = repository().save(mapper().toEntity(item));
    } else {
      itemEntity = repository().save(mapper().toEntity(item, findEntityById(item.getId())));
    }
    log.trace("Saved as entity {}", itemEntity);
    return toDomain(itemEntity);
  }

  @Override
  public T findById(ID id) throws NotFoundException {
    log.trace("Finding by id {}", id);
    return toDomain(findEntityById(id));
  }

  @Override
  public List<T> findAllById(Set<ID> ids) throws NotFoundException {
    log.trace("Finding by ids {}", ids);
    return repository().findAllById(ids).stream().map(this::toDomain).toList();
  }

  @Override
  public void deleteById(ID id) {
    log.trace("Deleting by id {}", id);
    repository().deleteById(id);
  }

  @NonNull
  public E findEntityById(@NonNull ID id) {
    log.trace("Finding entity by id {}", id);
    return repository().findById(id).orElseThrow(() -> notFoundException(id, null, null));
  }

  protected T toDomain(E entity) {
    return mapper().toDomain(entity);
  }

  @SuppressWarnings("unused")
  protected NotFoundException notFoundException(
      @Nullable ID id, @Nullable Persistent<ID> type, @Nullable PersistentEntity<ID> entity) {
    return new NotFoundException(Persistent.class, "id " + id);
  }
}
