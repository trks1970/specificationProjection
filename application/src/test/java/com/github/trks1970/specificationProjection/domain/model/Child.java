package com.github.trks1970.specificationProjection.domain.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.NonNull;

@Value
@Builder(toBuilder = true)
public class Child implements Named<Long> {

  Long id;
  @NonNull @lombok.NonNull String name;
  String description;

  @NonNull @lombok.NonNull Long parentId;
}
