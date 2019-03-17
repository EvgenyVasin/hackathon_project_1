package ru.mts.test.hackathon_project_1.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.EquipmentEntity;

@Repository
@Qualifier(value = "equipmentRepository")
public interface EquipmentRepository  extends CrudRepository<EquipmentEntity, Long> {
    EquipmentEntity findByName(String equipmentName);
}
