package ru.mts.test.hackathon_project_1.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.SmisEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Repository
@Qualifier(value = "smisRepository")
public interface SmisRepository extends CrudRepository<SmisEntity, Long> {
    Optional<SmisEntity> findById(Long id);

    List<SmisEntity> findAllByParentSmis(SmisEntity parent);
}
