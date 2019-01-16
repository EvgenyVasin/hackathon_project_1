package ru.basisintellect.support_smis.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.basisintellect.support_smis.entities.User;


/**
 * Created by safin.v on 23.10.2016.
 */
@Repository
@Qualifier(value = "userRepository")
public interface UsersRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
