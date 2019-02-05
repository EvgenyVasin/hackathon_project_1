package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.SmisFileEntity;

import java.util.Optional;

@Repository
@Qualifier(value = "smisFileRepository")
public interface SmisFileRepository extends CrudRepository <SmisFileEntity, Long>{

    Optional<SmisFileEntity> findByHash(String hash);

    Optional<SmisFileEntity> findById(Long id);

    Optional<SmisFileEntity> findByFileName(String fileName);
}
