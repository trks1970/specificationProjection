package com.github.trks1970.specificationProjection.infrastructure.mapper;

import com.github.trks1970.specificationProjection.domain.model.Child;
import com.github.trks1970.specificationProjection.infrastructure.entity.ChildEntity;
import com.github.trks1970.specificationProjection.infrastructure.entity.ParentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ChildEntityMapper implements EntityMapper<Long, Child, ChildEntity> {
  @Autowired private ParentEntityReferenceMapper parentEntityReferenceMapper;

  @Override
  @Mapping(target = "parentId", source = "parent.id")
  public abstract Child toDomain(ChildEntity entity);

  @Override
  @Mapping(target = "revision", ignore = true)
  @Mapping(target = "parent", source = "parentId")
  public abstract ChildEntity toEntity(Child type);

  @Override
  @Mapping(target = "revision", ignore = true)
  @Mapping(target = "parent", source = "parentId")
  public abstract ChildEntity toEntity(Child type, @MappingTarget ChildEntity entity);

  protected ParentEntity mapParentEntityId(Long id) {
    return parentEntityReferenceMapper.map(id);
  }
}
