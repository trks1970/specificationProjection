package com.github.trks1970.specificationProjection.infrastructure.mapper;

import com.github.trks1970.specificationProjection.domain.model.Parent;
import com.github.trks1970.specificationProjection.infrastructure.entity.ParentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ParentEntityMapper extends EntityMapper<Long, Parent, ParentEntity> {

  @Override
  @Mapping(target = "revision", ignore = true)
  ParentEntity toEntity(Parent type);

  @Override
  @Mapping(target = "revision", ignore = true)
  ParentEntity toEntity(Parent type, @MappingTarget ParentEntity entity);
}
