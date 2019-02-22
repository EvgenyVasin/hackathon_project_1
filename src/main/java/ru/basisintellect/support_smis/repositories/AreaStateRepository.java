package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.AreaStateEntity;
import ru.basisintellect.support_smis.model.entities.CityEntity;

import java.util.Optional;

@Repository
@Qualifier(value = "areaStateRepository")
public interface AreaStateRepository extends CrudRepository<AreaStateEntity, Long> {

}
