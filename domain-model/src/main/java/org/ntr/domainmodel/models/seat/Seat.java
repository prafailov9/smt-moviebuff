package org.ntr.domainmodel.models.seat;

import org.ntr.domainmodel.models.Model;
import org.ntr.domainmodel.models.theater.Theater;

import java.util.Objects;

public class Seat extends Model {

    private int seatNumber;
    private Theater theater; // one seat is unique to one hall


    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return seatNumber == seat.seatNumber &&
                getExternalId().equals(seat.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNumber, getExternalId());
    }
}
