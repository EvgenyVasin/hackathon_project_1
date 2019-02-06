package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.RegionEntity;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


@Repository
@Qualifier(value = "regionRepository")
public interface RegionRepository extends CrudRepository<RegionEntity, Long> {
    Optional<RegionEntity> findByName(String name);
    List<RegionEntity> findAll(Sort sort);
}

