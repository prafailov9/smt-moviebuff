package org.ntr.persistence.daos.screening;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ntr.persistence.coredb.AbstractGenericDaoTest;
import org.ntr.persistence.entities.ReservationEntity;
import org.ntr.persistence.entities.ScreeningEntity;

import java.util.List;

class DefaultScreeningDaoTest extends AbstractGenericDaoTest<ScreeningEntity> {

    private DefaultScreeningDao screeningDao;

    @BeforeEach
    public void setUp() {
        screeningDao = new DefaultScreeningDao();
        ScreeningEntity screeningEntity = new ScreeningEntity();
        ReservationEntity reservationEntity = new ReservationEntity();
//        screeningEntity.getEntityRelations();
//        reservationEntity.getEntityRelations();
    }

    @Test
    public void testSaveWithReferences() {



    }

    @Override
    protected List<ScreeningEntity> getRecords() {
        return screeningDao.loadAll();
    }

    @Override
    protected Long getDtoId(ScreeningEntity dto) {
        return dto.getId();
    }
}