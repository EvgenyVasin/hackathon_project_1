package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.CountryEntity;



import java.util.List;

@Repository
@Qualifier(value = "countryRepository")
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
    List<CountryEntity> findAll(Sort sort);
}
