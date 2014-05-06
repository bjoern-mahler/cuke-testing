package de.spinwork.testapp.base;

import org.springframework.data.repository.CrudRepository;

public interface SomeEntityRepository extends CrudRepository<SomeEntity, Long> {
}
