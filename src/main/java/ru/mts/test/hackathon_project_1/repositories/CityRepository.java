package ru.mts.test.hackathon_project_1.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.CityEntity;
import ru.mts.test.hackathon_project_1.model.entities.RegionEntity;

import java.util.List;

@Repository
@Qualifier(value = "cityRepository")
public interface CityRepository extends CrudRepository<CityEntity, Long> {
    List<CityEntity> findAll(Sort sort);
    List<CityEntity> findByRegion(RegionEntity regionEntity);
    CityEntity  findByName(String name);
}
