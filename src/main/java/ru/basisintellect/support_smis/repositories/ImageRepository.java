package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.ImageEntity;

@Repository
@Qualifier(value = "imageRepository")
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    ImageEntity findByImgLink(String imgLink);
}
