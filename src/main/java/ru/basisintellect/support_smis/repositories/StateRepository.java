package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.SmisEntity;
import ru.basisintellect.support_smis.model.entities.StateEntity;

@Repository
@Qualifier(value = "stateRepository")
public interface StateRepository  extends CrudRepository<StateEntity, Long> {
}
