package ru.basisintellect.support_smis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.basisintellect.support_smis.model.entities.SmisTypeEntity;

public interface SmisTypeRepository  extends CrudRepository<SmisTypeEntity, Long> {
}
