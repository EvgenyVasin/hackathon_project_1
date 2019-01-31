package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.RegionEntity;
import ru.basisintellect.support_smis.model.entities.SmisEntity;
import ru.basisintellect.support_smis.model.entities.SmisEquipmentEntity;

@Repository
@Qualifier(value = "smisEquipmentRepository")
public interface SmisEquipmentRepository extends CrudRepository<SmisEquipmentEntity, Long> {

    SmisEntity findAllById(Long parent_smis_id);

    Iterable<? extends SmisEquipmentEntity> findAllBySmisId(Long smisId);
}
