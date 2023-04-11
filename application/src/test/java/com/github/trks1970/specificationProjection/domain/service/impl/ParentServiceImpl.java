package com.github.trks1970.specificationProjection.domain.service.impl;

import com.github.trks1970.specificationProjection.domain.exception.NotFoundException;
import com.github.trks1970.specificationProjection.domain.model.Child;
import com.github.trks1970.specificationProjection.domain.model.Parent;
import com.github.trks1970.specificationProjection.domain.repository.ChildRepository;
import com.github.trks1970.specificationProjection.domain.repository.NamedTypeRepository;
import com.github.trks1970.specificationProjection.domain.repository.ParentRepository;
import com.github.trks1970.specificationProjection.domain.service.ParentService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

  private final ParentRepository parentRepository;
  private final ChildRepository childRepository;

  @Override
  public NamedTypeRepository<Long, Parent> repository() {
    return parentRepository;
  }

  public Optional<Parent> findByUniqueName(@NonNull String name) {

    return Optional.ofNullable(
        parentRepository.findByName(name).stream()
            .findFirst()
            .orElseThrow(() -> new NotFoundException(Parent.class, "name=[" + name + "]")));
  }

  @Override
  public void addChild(Child child) {
    childRepository.save(child);
  }
}
