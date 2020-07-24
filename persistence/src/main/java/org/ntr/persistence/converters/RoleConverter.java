package org.ntr.persistence.converters;

import org.ntr.domainmodel.models.role.Role;
import org.ntr.domainmodel.models.role.RoleName;
import org.ntr.persistence.entities.RoleEntity;

public class RoleConverter implements Converter<Role, RoleEntity> {


    @Override
    public Role toEntity(RoleEntity dto) {
        final Role role = new Role();
        role.setRoleName(RoleName.valueOf(dto.getRoleName()));
        return role;
    }

    @Override
    public RoleEntity toDto(Role entity) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(entity.getRoleName().getRoleName());
        return roleEntity;
    }
}
