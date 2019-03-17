package ru.mts.test.hackathon_project_1.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.DistrictEntity;
import ru.mts.test.hackathon_project_1.model.entities.RegionEntity;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


@Repository
@Qualifier(value = "regionRepository")
public interface RegionRepository extends CrudRepository<RegionEntity, Long> {
    Optional<RegionEntity> findByName(String name);
    List<RegionEntity> findAll(Sort sort);
    List<RegionEntity>findByDistrict(DistrictEntity districtEntity);

}

