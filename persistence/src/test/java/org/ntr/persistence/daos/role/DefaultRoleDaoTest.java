package org.ntr.persistence.daos.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ntr.persistence.coredb.AbstractGenericDaoTest;
import org.ntr.persistence.entities.RoleEntity;

import java.util.List;

class DefaultRoleDaoTest extends AbstractGenericDaoTest<RoleEntity> {

    private DefaultRoleDao roleDao;

    @BeforeEach
    public void setUp() {
        roleDao = new DefaultRoleDao();
    }


    @Test
    public void loadByIdTest() {
        RoleEntity dto = roleDao.loadById(getRandomId());

    }

    @Test
    public void saveRoleDtoTest() {
        final String roleName = "new_role";
        RoleEntity dto = new RoleEntity();
        dto.setRoleName(roleName); // need to add checks if the role name exists
        RoleEntity savedDto = roleDao.save(dto);
        Assertions.assertNotNull(savedDto);
        Assertions.assertEquals(dto, savedDto);
        Assertions.assertEquals(dto.getRoleName(), savedDto.getRoleName());
    }

    @Test
    public void updateOneTest() {
        final Long id = getRandomId();
        final String updatedRoleName = "MAAFAKA";
        RoleEntity roleEntity = roleDao.loadById(id);
        roleEntity.setRoleName(updatedRoleName);
        boolean success = roleDao.update(roleEntity);
        Assertions.assertTrue(success);

        RoleEntity updatedRoleEntity = roleDao.loadById(id);
        Assertions.assertNotNull(updatedRoleEntity);
    }

    @Test
    public void loadByRoleNameTest() {
        final String roleName = "ADMIN";
        RoleEntity roleEntity = roleDao.loadOneByRoleName(roleName);

        Assertions.assertNotNull(roleEntity);
        Assertions.assertEquals(roleName, roleEntity.getRoleName());
    }

    @Override
    protected List<RoleEntity> getRecords() {
        return roleDao.loadAll();
    }

    @Override
    protected Long getDtoId(RoleEntity dto) {
        return dto.getId();
    }
}