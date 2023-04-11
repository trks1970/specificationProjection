package com.github.trks1970.specificationProjection.domain.service;

import com.github.trks1970.specificationProjection.domain.model.Child;
import com.github.trks1970.specificationProjection.domain.model.Parent;
import java.util.Optional;
import org.springframework.lang.NonNull;

public interface ParentService extends NamedTypeService<Long, Parent> {
  Optional<Parent> findByUniqueName(@NonNull String name);

  void addChild(Child child);
}
