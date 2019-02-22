package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.CityEntity;
import ru.basisintellect.support_smis.model.entities.RegionEntity;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier(value = "cityRepository")
public interface CityRepository extends CrudRepository<CityEntity, Long> {
    List<CityEntity> findAll(Sort sort);
    List<CityEntity> findByRegion(RegionEntity regionEntity);
    CityEntity  findByName(String name);
}
