package ru.basisintellect.support_smis.entities;

import javax.persistence.*;

/**
 * Created by safin.v on 24.10.2016.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer userRoleId;

    @Column(name = "role_name", nullable = false, unique = true, length = 128)
    private String userRoleName;



    /**
     * @return the userRoleId
     */
    public Integer getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId the userRoleId to set
     */
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

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



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserRole [userRoleId=" + userRoleId + ", userRoleName="
                + userRoleName + "]";
    }

}
