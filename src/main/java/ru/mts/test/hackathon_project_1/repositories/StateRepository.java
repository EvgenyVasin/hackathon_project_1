package ru.mts.test.hackathon_project_1.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.StateEntity;

@Repository
@Qualifier(value = "stateRepository")
public interface StateRepository  extends CrudRepository<StateEntity, Long> {
}
