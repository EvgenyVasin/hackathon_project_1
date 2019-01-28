package ru.basisintellect.support_smis.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.SmisEntity;

import java.util.Optional;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Repository
@Qualifier(value = "smisRepository")
public interface SmisRepository extends CrudRepository<SmisEntity, Long> {
    Optional<SmisEntity> findById(Long id);
}
