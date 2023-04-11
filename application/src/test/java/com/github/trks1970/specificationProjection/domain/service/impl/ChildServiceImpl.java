package com.github.trks1970.specificationProjection.domain.service.impl;

import com.github.trks1970.specificationProjection.domain.model.Child;
import com.github.trks1970.specificationProjection.domain.repository.ChildRepository;
import com.github.trks1970.specificationProjection.domain.repository.NamedTypeRepository;
import com.github.trks1970.specificationProjection.domain.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {
  private final ChildRepository childRepository;

  @Override
  public NamedTypeRepository<Long, Child> repository() {
    return childRepository;
  }
}
