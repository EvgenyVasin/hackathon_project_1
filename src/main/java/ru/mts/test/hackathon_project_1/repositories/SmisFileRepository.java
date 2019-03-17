package ru.mts.test.hackathon_project_1.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.SmisFileEntity;

import java.util.Optional;

@Repository
@Qualifier(value = "smisFileRepository")
public interface SmisFileRepository extends CrudRepository <SmisFileEntity, Long>{


    Optional<SmisFileEntity> findById(Long id);

    Optional<SmisFileEntity> findByFileName(String fileName);
}
