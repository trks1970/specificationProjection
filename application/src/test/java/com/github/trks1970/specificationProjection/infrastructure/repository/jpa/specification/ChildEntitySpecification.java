package com.github.trks1970.specificationProjection.infrastructure.repository.jpa.specification;

import com.github.trks1970.specificationProjection.infrastructure.entity.ChildEntity;
import com.github.trks1970.specificationProjection.infrastructure.entity.ChildEntity_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChildEntitySpecification {

  public static Specification<ChildEntity> name(String name) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get(ChildEntity_.NAME), name);
  }

  public static Specification<ChildEntity> parent(Long parentId) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get(ChildEntity_.PARENT), parentId);
  }
}
