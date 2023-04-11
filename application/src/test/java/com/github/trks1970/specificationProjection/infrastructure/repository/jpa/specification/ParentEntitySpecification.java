package com.github.trks1970.specificationProjection.infrastructure.repository.jpa.specification;

import com.github.trks1970.specificationProjection.infrastructure.entity.ParentEntity;
import com.github.trks1970.specificationProjection.infrastructure.entity.ParentEntity_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParentEntitySpecification {

  public static Specification<ParentEntity> name(String name) {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(ParentEntity_.NAME), name);
    };
  }
}
