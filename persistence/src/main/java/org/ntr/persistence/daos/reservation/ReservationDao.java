package org.ntr.persistence.daos.reservation;

import org.ntr.persistence.entities.ReservationEntity;
import org.ntr.persistence.entities.ScreeningEntity;
import org.ntr.persistence.entities.UserEntity;

import java.util.List;

public interface ReservationDao {

    List<ReservationEntity> loadAllByScreening(ScreeningEntity screeningEntity);
    List<ReservationEntity> loadAllByUser(UserEntity userEntity);

}
