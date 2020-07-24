package org.ntr.domainmodel.models.reservation;

import org.ntr.domainmodel.models.Model;
import org.ntr.domainmodel.models.screening.Screening;
import org.ntr.domainmodel.models.user.User;

import java.util.Objects;

// class will be able to reserve seats through the screening, chosen by the user
public class Reservation extends Model {

    private String reservationName;

    private int seatsToReserve;

    private User user;

    private Screening screening;


    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public int getSeatsToReserve() {
        return seatsToReserve;
    }

    public void setSeatsToReserve(int seatsToReserve) {
        this.seatsToReserve = seatsToReserve;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationName.equals(that.reservationName) &&
                getExternalId().equals(that.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationName, getExternalId());
    }
}
