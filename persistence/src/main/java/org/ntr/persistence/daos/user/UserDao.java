package org.ntr.persistence.daos.user;

import org.ntr.persistence.entities.RoleEntity;
import org.ntr.persistence.entities.UserEntity;

import java.util.List;

public interface UserDao {
    List<UserEntity> loadAllByRole(RoleEntity role);
}
