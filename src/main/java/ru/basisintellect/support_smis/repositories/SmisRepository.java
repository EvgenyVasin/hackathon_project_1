package ru.basisintellect.support_smis.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.entities.Smis;

import java.util.Optional;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Repository
@Qualifier(value = "smisRepository")
public interface SmisRepository extends CrudRepository<Smis, Long> {
    Optional<Smis> findById(Long id);
}
