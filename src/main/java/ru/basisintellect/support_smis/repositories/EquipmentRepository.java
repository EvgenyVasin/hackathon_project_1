package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.EquipmentEntity;

@Repository
@Qualifier(value = "equipmentRepository")
public interface EquipmentRepository  extends CrudRepository<EquipmentEntity, Long> {
}
