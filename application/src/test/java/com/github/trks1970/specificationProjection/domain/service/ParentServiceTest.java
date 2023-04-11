package com.github.trks1970.specificationProjection.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.trks1970.specificationProjection.TestJPAConfig;
import com.github.trks1970.specificationProjection.domain.model.Child;
import com.github.trks1970.specificationProjection.domain.model.Parent;
import com.github.trks1970.specificationProjection.domain.service.impl.ParentServiceImpl;
import com.github.trks1970.specificationProjection.infrastructure.mapper.ChildEntityMapperImpl;
import com.github.trks1970.specificationProjection.infrastructure.mapper.ParentEntityMapperImpl;
import com.github.trks1970.specificationProjection.infrastructure.mapper.ParentEntityReferenceMapper;
import com.github.trks1970.specificationProjection.infrastructure.repository.ChildEntityRepositoryImpl;
import com.github.trks1970.specificationProjection.infrastructure.repository.ParentEntityRepositoryImpl;
import com.github.trks1970.specificationProjection.infrastructure.repository.jpa.JpaParentEntityRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {
      TestJPAConfig.class,
      ParentServiceImpl.class,
      ParentEntityMapperImpl.class,
      ParentEntityRepositoryImpl.class,
      ChildEntityRepositoryImpl.class,
      ChildEntityMapperImpl.class,
      ParentEntityReferenceMapper.class
    })
class ParentServiceTest {

  @Autowired ParentService service;

  @Autowired JpaParentEntityRepository repository;

  @BeforeEach
  void setup() {
    repository.deleteAll();
  }

  @Test
  void create() {

    Parent persistent = service.save(Parent.builder().name("persistent").build());

    assertThat(persistent.getId()).isNotNull();
  }

  @Test
  void update() {
    Parent persistent = service.save(Parent.builder().name("persistent").build());

    Parent updated = service.save(persistent.toBuilder().name("updated").build());

    assertThat(persistent.getId()).isEqualTo(updated.getId());
    assertThat(persistent.getName()).isEqualTo("persistent");
    assertThat(updated.getName()).isEqualTo("updated");
  }

  @Test
  void findById() {
    Parent persistent = service.save(Parent.builder().name("persistent").build());

    Parent found = service.findById(persistent.getId());

    assertThat(found).usingRecursiveComparison().isEqualTo(persistent);
  }

  @Test
  void findAllById() {
    Parent persistent1 = service.save(Parent.builder().name("persistent1").build());
    Parent persistent2 = service.save(Parent.builder().name("persistent2").build());

    List<Parent> all = service.findAllById(Set.of(persistent1.getId(), persistent2.getId()));

    assertThat(all).containsExactlyInAnyOrder(persistent1, persistent2);
  }

  @Test
  void deleteById() {
    service.save(Parent.builder().name("persistent1").build());
    Parent persistent2 = service.save(Parent.builder().name("persistent2").build());

    service.deleteById(persistent2.getId());

    assertThat(repository.findAll()).hasSize(1);
  }

  @Test
  void findByName() {
    Parent persistent = service.save(Parent.builder().name("persistent1").build());

    Set<Parent> found = service.findByName("persistent1");

    assertThat(found).hasSize(1);
    assertThat(found.iterator().next()).usingRecursiveComparison().isEqualTo(persistent);
  }

  @Test
  void findByNameNotFound() {
    service.save(Parent.builder().name("persistent1").build());

    Set<Parent> found = service.findByName("persistent2");

    assertThat(found).isEmpty();
  }

  @Test
  void findByUniqueName() {
    service.save(Parent.builder().name("persistent1").build());

    Optional<Parent> found = service.findByUniqueName("persistent1");

    assertThat(found).isPresent();
  }

  @Test
  void deleteWithChildren() {
    Parent parent = service.save(Parent.builder().name("parent").build());
    Child child = Child.builder().name("child").parentId(parent.getId()).build();
    service.addChild(child);

    service.deleteById(parent.getId());

    assertThat(repository.findAll()).isEmpty();
  }
}
