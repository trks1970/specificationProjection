package com.github.trks1970.specificationProjection.domain.model;

import java.io.Serializable;

public interface Named<ID extends Serializable> extends Persistent<ID> {
  String getName();

  String getDescription();
}
