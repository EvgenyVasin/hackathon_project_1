package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.model.entities.ContactEntity;

import java.util.Optional;

@Repository
@Qualifier(value = "contactsRepository")
public interface ContactsRepository extends CrudRepository<ContactEntity, Long> {
    Optional<ContactEntity> findById(Long id);
}
