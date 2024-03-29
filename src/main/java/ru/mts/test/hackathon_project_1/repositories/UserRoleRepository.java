package ru.mts.test.hackathon_project_1.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mts.test.hackathon_project_1.model.entities.UserRoleEntity;


/**
 * Created by safin.v on 24.10.2016.
 */
@Repository
@Qualifier(value = "userRoleRepository")
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Integer> {
    public UserRoleEntity findByUserRoleName(String userRoleName);
}