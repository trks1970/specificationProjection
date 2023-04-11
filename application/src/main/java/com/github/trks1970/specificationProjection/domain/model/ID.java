package com.github.trks1970.specificationProjection.domain.model;

import java.io.Serializable;

public interface ID<T extends Serializable> {
  T getId();
}
