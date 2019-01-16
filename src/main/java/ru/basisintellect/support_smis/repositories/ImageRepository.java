package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.entities.Image;

/**
 * Created by vasin.e on .
 */

@Repository
@Qualifier(value = "imageRepository")
public interface ImageRepository extends CrudRepository<Image, Long> {
    Image findByImgLink(String imgLink);
}
