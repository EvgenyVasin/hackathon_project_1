package ru.mts.test.hackathon_project_1.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.SmisEntity;
import ru.mts.test.hackathon_project_1.model.entities.SmisEquipmentEntity;

@Repository
@Qualifier(value = "smisEquipmentRepository")
public interface SmisEquipmentRepository extends CrudRepository<SmisEquipmentEntity, Long> {

    SmisEntity findAllById(Long parent_smis_id);

    Iterable<? extends SmisEquipmentEntity> findAllBySmisId(Long smisId);
}
