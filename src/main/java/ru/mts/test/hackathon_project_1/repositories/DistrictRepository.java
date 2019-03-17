package ru.mts.test.hackathon_project_1.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.CountryEntity;
import ru.mts.test.hackathon_project_1.model.entities.DistrictEntity;


import java.util.List;

@Repository
@Qualifier(value = "districtRepository")
public interface DistrictRepository extends CrudRepository<DistrictEntity, Long> {
    List<DistrictEntity> findAll(Sort sort);
    List<DistrictEntity> findByCountry(CountryEntity countryEntity);
}
