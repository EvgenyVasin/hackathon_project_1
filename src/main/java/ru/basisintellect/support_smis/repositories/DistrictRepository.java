package ru.basisintellect.support_smis.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.DistrictEntity;
import ru.basisintellect.support_smis.model.entities.RegionEntity;

import java.util.List;

@Repository
@Qualifier(value = "districtRepository")
public interface DistrictRepository extends CrudRepository<DistrictEntity, Long> {
    List<DistrictEntity> findAll(Sort sort);
}
