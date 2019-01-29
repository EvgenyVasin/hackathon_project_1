package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;

/**
 * Created by safin.v on 24.10.2016.
 */
@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends CustomEntity{


    @Column(name = "role_name", nullable = false, unique = true, length = 128)
    private String userRoleName;



    /**
     * @return the userRoleName
     */
    public String getUserRoleName() {
        return userRoleName;
    }

    /**
     * @param userRoleName the userRoleName to set
     */
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }


    public UserRoleEntity() {
    }

    public UserRoleEntity(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserRoleEntity [userRoleId=" + this.getId() + ", userRoleName="
                + userRoleName + "]";
    }

}
