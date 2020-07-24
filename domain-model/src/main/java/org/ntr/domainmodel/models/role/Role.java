package org.ntr.domainmodel.models.role;

import org.ntr.domainmodel.models.Model;
import org.ntr.domainmodel.models.user.User;

import java.util.List;
import java.util.Objects;

public class Role extends Model {

    private RoleName roleName;

    private List<User> users; // one role is used by many users


    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) { this.roleName = roleName; }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleName == role.roleName &&
                getExternalId().equals(role.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, getExternalId());
    }
}
