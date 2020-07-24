package org.ntr.persistence.daos.role;

import org.ntr.persistence.entities.RoleEntity;

public interface RoleDao {

    RoleEntity loadOneByRoleName(final String roleName);

}

