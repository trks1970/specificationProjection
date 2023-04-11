package com.github.trks1970.specificationProjection.domain.exception;

public class NotFoundException extends DomainException {
  public NotFoundException(Class<?> type, String lookup) {
    super(type.getSimpleName() + " not found by " + lookup);
  }
}
